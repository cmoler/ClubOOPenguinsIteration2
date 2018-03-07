package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.MenuController;

import java.awt.event.KeyEvent;

public class MenuState implements ControllerState {

    ControllerMediator controllerMediator;
    MenuController menuController;

    public MenuState(ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
    }

    @Override
    public void process(KeyEvent keyEvent) {

    }

    @Override
    public void loadKeyBindings() {

    }

}
