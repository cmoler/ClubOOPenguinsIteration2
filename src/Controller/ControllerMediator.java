package Controller;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.Serializer;
import Controller.States.*;
import Model.Updateable;
import View.MenuView.MenuViewPort;
import View.StatusView.StatusViewPort;
import View.Viewport;
import Controller.Input.Input;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerMediator {

    private ControllerState activeState;

    private OOPenguinGameFrame gameFrame;

    private PlayerState entityState;
    private MenuState menuState;
    private InventoryState inventoryState;
    private EquipmentState equipmentState;
    private SkillsState skillsState;

    private KeyBindingState keyBindingState;

    private GameBuilder gameBuilder;

    private List<Updateable> updateables;

    private Viewport viewport;
    private StatusViewPort statusViewPort;
    private MenuViewPort menuViewPort;

    private Input input;

    private Timer timer;

    // initial load
    public ControllerMediator(){
        gameBuilder = new GameBuilder();
        updateables = gameBuilder.getUpdateables();
        getViewsFromBuilder();
        loadStates();
        attachInputToViews();
        changeToMenuState();
        startTimer();
    }

    private void getViewsFromBuilder(){
        menuViewPort = gameBuilder.getMainMenuViewport();
        gameFrame = gameBuilder.getGameFrame();
    }

    private void loadStates(){
        keyBindingState = new KeyBindingState(this);
        menuState = new MenuState(gameBuilder,this);
        activeState = menuState;
        entityState = new PlayerState(gameBuilder, this);
        inventoryState = new InventoryState(gameBuilder, this);
        equipmentState = new EquipmentState(gameBuilder, this);
        skillsState = new SkillsState(gameBuilder, this);
    }

    private void attachInputToViews(){
        input = new Input(activeState);
        gameFrame.addKeyListener(input);
        menuViewPort.addKeyListener(input);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            for(int i = 0; i < updateables.size(); ++i){
                updateables.get(i).update();
            }
            if(viewport != null) viewport.repaint();
            if(menuViewPort != null) menuViewPort.repaint();
        }
    }

    public void changeToEntityState(){
        input.setActiveState(entityState);
        entityState.setActive();
        activeState = entityState;
    }

    public void changeToMenuState(){
        input.setActiveState(menuState);
        menuState.setActive();
        activeState = menuState;
    }

    public void changeToInventoryState(){
        input.setActiveState(inventoryState);
        inventoryState.setActive();
        statusViewPort.switchToInventory();
        activeState = inventoryState;
    }

    public void changeToEquipmentState(){
        input.setActiveState(equipmentState);
        equipmentState.setActive();
        statusViewPort.switchToEquipment();
        activeState = equipmentState;
    }

    public void changeToSkillsState(){
        input.setActiveState(skillsState);
        skillsState.setActive();
        statusViewPort.switchToSkills();
        activeState = skillsState;
    }

    public void primeKeyBindingState(String bindingToChange, String keyToChange){
        keyBindingState.setBindingToChange(bindingToChange);
        keyBindingState.setKeyToChange(keyToChange);
    }

    public void changeToKeyBindingState(){
        input.setActiveState(keyBindingState);
        activeState = keyBindingState;
    }

    public void reloadKeyBindings(){
        gameBuilder.getKeyBindings().loadKeyBindings();
        entityState.loadKeyBindings();
        menuState.loadKeyBindings();
        inventoryState.loadKeyBindings();
        equipmentState.loadKeyBindings();
        skillsState.loadKeyBindings();
    }

    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

}
