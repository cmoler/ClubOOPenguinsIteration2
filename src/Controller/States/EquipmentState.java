package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.EquipmentController;
import Controller.SavingLoading.GameLoader;

import java.awt.event.KeyEvent;

// needed key bindings:
/*
exit equipment
change to inventory
change to player
unequip item
move slot item is in


 */

public class EquipmentState implements ControllerState {

    ControllerMediator controllerMediator;
    EquipmentController equipmentController;

    public EquipmentState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.equipmentController = new EquipmentController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {

    }

    @Override
    public void loadKeyBindings() {

    }
}
