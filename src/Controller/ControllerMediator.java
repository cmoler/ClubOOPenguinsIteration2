package Controller;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.Serializer;
import Controller.States.*;
import Model.UpdateList;
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

    private boolean menu = true;

    private Viewport viewport;
    private StatusViewPort statusViewPort;
    private MenuViewPort menuViewPort;

    private boolean load = false;

    private Input input;

    private Timer timer;

    // initial load
    public ControllerMediator(){
        gameBuilder = new GameBuilder();
        getViewsFromBuilder();
        loadStates();
        attachInputToViews();
        changeToMenuState();
        startTimer();
    }

    private void getViewsFromBuilder(){
        menuViewPort = gameBuilder.getMainMenuViewport();
        statusViewPort = gameBuilder.getStatusViewPort();
        viewport = gameBuilder.getViewport();
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

    private void attachViewsToGameFrame(){
        gameFrame.add(viewport);
        viewport.addKeyListener(input);
    }

    public void load() {
        getViewsFromBuilder();
        loadStates();
        attachInputToViews();
        attachViewsToGameFrame();
        changeToEntityState();
        load = true;
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            if(load) UpdateList.getInstance().update();
            if(viewport != null && !menu) viewport.repaint();
            if(menuViewPort != null && menu) menuViewPort.repaint();
        }
    }

    public void changeToEntityState(){
        menu = false;
        input.setActiveState(entityState);
        viewport.setVisible(true);
        menuViewPort.setVisible(false);
        viewport.requestFocus();
        activeState = entityState;
    }

    public void changeToMenuState(){
        input.setActiveState(menuState);
        menuViewPort.setVisible(true);
        menu = true;
        //viewport.setVisible(false);
        //statusViewPort.setVisible(false);
        menuViewPort.requestFocus();
        activeState = menuState;
    }

    public void changeToInventoryState(){
        menu = false;
        input.setActiveState(inventoryState);
        viewport.setVisible(true);
        menuViewPort.setVisible(false);
        viewport.requestFocus();
        statusViewPort.switchToInventory();
        activeState = inventoryState;
    }

    public void changeToEquipmentState(){
        menu = false;
        input.setActiveState(equipmentState);
        viewport.setVisible(true);
        menuViewPort.setVisible(false);
        viewport.requestFocus();
        statusViewPort.switchToEquipment();
        activeState = equipmentState;
    }

    public void changeToSkillsState(){
        menu = false;
        input.setActiveState(skillsState);
        viewport.setVisible(true);
        menuViewPort.setVisible(false);
        viewport.requestFocus();
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
