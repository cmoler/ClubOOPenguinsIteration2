package Controller.Controllers;

import Controller.ControllerMediator;

public class MenuController {

    private int currentlySelected = 1;
    private ControllerMediator controllerMediator;

    public MenuController(ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
    }

    public void scrollUp(){
        currentlySelected = -1;
        controllerMediator.setSelectedMenuView(currentlySelected);
    }
}
