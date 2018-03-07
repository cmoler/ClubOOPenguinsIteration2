package Controller;

import Controller.States.*;

public class ControllerMediator {

    private ControllerState activeState;

    private EntityState entityState;
    private MenuState menuState;
    private InventoryState inventoryState;
    private EquipmentState equipmentState;
    private SkillsState skillsState;

    // initial load
    public ControllerMediator(){
        this.menuState = new MenuState(this);
        changeToMenuState();
    }

    // when loading a save game/new game
    public ControllerMediator(String fileName){

    }

    public void changeToEntityState(){
        this.activeState = entityState;
    }

    public void changeToMenuState(){
        this.activeState = menuState;
    }

    public void changeToInventoryState(){
        this.activeState = inventoryState;
    }

    public void changeToEquipmentState(){
        this.activeState = equipmentState;
    }

    public void changeToSkillsState(){
        this.activeState = skillsState;
    }
}
