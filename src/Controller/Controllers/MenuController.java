package Controller.Controllers;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.MenuViewPort;

public class MenuController implements Controller{

    private int currentlySelected = 1;
    private ControllerMediator controllerMediator;

    private MenuViewPort menuViewPort;

    public MenuController(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.menuViewPort = gameLoader.getMenuViewport();
        this.controllerMediator = controllerMediator;
    }

    @Override
    public void setActive() {
        menuViewPort.setVisible(true);
        menuViewPort.requestFocus();
    }

    public void scrollUp(){
        currentlySelected = -1;
        menuViewPort.setSelectedMenuView(currentlySelected);
    }

    public void select() {
        switch (currentlySelected){
            case 0:
                break;
            case 1:
                break;
            case 2:

        }
    }

}
