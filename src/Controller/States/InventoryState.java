package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.InventoryController;
import Controller.SavingLoading.GameLoader;

import java.awt.event.KeyEvent;

public class InventoryState implements ControllerState {

    // needed key bindings:
/*
exit inventory
change to equipment
use item
equip item

 */

    ControllerMediator controllerMediator;
    InventoryController inventoryController;

    public InventoryState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.inventoryController = new InventoryController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {

    }

    @Override
    public void loadKeyBindings() {

    }
}
