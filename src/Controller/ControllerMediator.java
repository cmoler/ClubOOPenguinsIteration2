package Controller;

import Controller.SavingLoading.GameLoader;
import Controller.SavingLoading.GameSaver;
import Controller.States.*;
import View.AreaView.AreaEffectView;
import View.AreaView.AreaViewPort;
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

    private GameLoader gameLoader;
    private GameSaver gameSaver;

    private Viewport areaViewport;
    private MenuViewPort menuViewPort;

    private Input input;

    private Timer timer;

    // initial load
    public ControllerMediator(){

        gameLoader = new GameLoader();
        gameSaver = new GameSaver();

        areaViewport = new AreaViewPort();

        menuViewPort = gameLoader.getMenuViewport();
        gameFrame = gameLoader.getGameFrame();

        menuState = new MenuState(gameLoader,this);
        changeToMenuState();
        input = new Input(activeState);
        gameFrame.addKeyListener(input);
        menuViewPort.addKeyListener(input);


        entityState = new EntityState(gameLoader, this);
        inventoryState = new InventoryState(gameLoader, this);
        equipmentState = new EquipmentState(gameLoader, this);
        skillsState = new SkillsState(gameLoader, this);

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);

    }

    // when loading a save game/new game
    public ControllerMediator(String fileName){

    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            if(areaViewport != null) areaViewport.repaint();
            if(menuViewPort != null) menuViewPort.repaint();
        }
    }

    public void changeToEntityState(){
        entityState.setActive();
        activeState = entityState;
    }

    public void changeToMenuState(){
        menuState.setActive();
        activeState = menuState;
    }

    public void changeToInventoryState(){
        inventoryState.setActive();
        activeState = inventoryState;
    }

    public void changeToEquipmentState(){
        equipmentState.setActive();
        activeState = equipmentState;
    }

    public void changeToSkillsState(){
        skillsState.setActive();
        activeState = skillsState;
    }

    public void loadGame(){

    }

}
