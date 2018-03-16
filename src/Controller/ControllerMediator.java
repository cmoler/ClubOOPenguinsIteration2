package Controller;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.GameSaver;
import Controller.States.*;
import View.MenuView.MenuViewPort;
import View.Viewport;
import Controller.Input.Input;

import java.util.Timer;
import java.util.TimerTask;

public class ControllerMediator {

    private ControllerState activeState;

    private OOPenguinGameFrame gameFrame;

    private EntityState entityState;
    private MenuState menuState;
    private InventoryState inventoryState;
    private EquipmentState equipmentState;
    private SkillsState skillsState;

    private KeyBindingState keyBindingState;

    private GameBuilder gameBuilder;
    private GameSaver gameSaver;

    private Viewport areaViewport;
    private MenuViewPort menuViewPort;

    private Input input;

    private Timer timer;

    // initial load
    public ControllerMediator(){

        gameBuilder = new GameBuilder();
        gameSaver = new GameSaver();

        getViewsFromLoader();
        loadStates();
        attachInputToViews();
        changeToMenuState();

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
    }

    private void getViewsFromLoader(){
        menuViewPort = gameBuilder.getMainMenuViewport();
        gameFrame = gameBuilder.getGameFrame();
    }

    private void loadStates(){
        keyBindingState = new KeyBindingState(this);
        menuState = new MenuState(gameBuilder,this);
        activeState = menuState;
        entityState = new EntityState(gameBuilder, this);
        inventoryState = new InventoryState(gameBuilder, this);
        equipmentState = new EquipmentState(gameBuilder, this);
        skillsState = new SkillsState(gameBuilder, this);
    }

    private void attachInputToViews(){
        input = new Input(activeState);
        gameFrame.addKeyListener(input);
        menuViewPort.addKeyListener(input);
    }

    // when loading a save game/new game: <- I (JAD) dont think this should be here; should be in GameBuilder
    public void loadGame(String fileName){
        gameBuilder.loadGame(fileName);
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            if(areaViewport != null) areaViewport.repaint();
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
        activeState = inventoryState;
    }

    public void changeToEquipmentState(){
        input.setActiveState(equipmentState);
        equipmentState.setActive();
        activeState = equipmentState;
    }

    public void changeToSkillsState(){
        input.setActiveState(skillsState);
        skillsState.setActive();
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

}
