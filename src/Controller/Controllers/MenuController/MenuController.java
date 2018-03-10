package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.Controllers.Controller;
import Controller.SavingLoading.GameLoader;
import View.MenuView.MenuViewPort;

public class MenuController implements Controller{

    private int currentlySelected = 0;
    private ControllerMediator controllerMediator;

    private MenuViewPort menuViewPort;

    private MenuController parent;
    private MenuController activeController = null;
    private NewGameController newGameController;
    private SaveGameController saveGameController;
    private LoadGameController loadGameController;
    private OptionsController optionsController;
    private ExitGameController exitGameController;

    public MenuController(){

    }

    public MenuController(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.menuViewPort = gameLoader.getMenuViewport();
        this.controllerMediator = controllerMediator;

        newGameController = new NewGameController(gameLoader, this);
        saveGameController = new SaveGameController(gameLoader, this);
        loadGameController = new LoadGameController(gameLoader, this);
        optionsController = new OptionsController(gameLoader, this);
        exitGameController = new ExitGameController(gameLoader, this);
    }

    protected void setParent(MenuController menuController){
        this.parent = menuController;
    }

    public void exitSubMenu(){
        System.out.println("Exit menu");
        menuViewPort.returnToMenu();
        this.activeController = null;
    }

    @Override
    public void setActive() {
        menuViewPort.setVisible(true);
        menuViewPort.requestFocus();
    }

    public void scrollDown(){
        if(activeController != null) {
            activeController.scrollDown();
            return;
        }
        currentlySelected += 1;
        menuViewPort.setSelectedMenuView(-1);
    }

    public void scrollUp(){
        if(activeController != null) {
            activeController.scrollUp();
            return;
        }
        currentlySelected -= 1;
        menuViewPort.setSelectedMenuView(1);
    }

    public void select() {
        if(activeController != null) {
            activeController.select();
            return;
        }
        switch (currentlySelected){
            case 0:
                menuViewPort.enterNewGameView();
                activeController = newGameController;
                break;
            case 1:
                menuViewPort.enterSaveGameView();
                activeController = saveGameController;
                break;
            case 2:
                menuViewPort.enterLoadGameView();
                activeController = loadGameController;
            case 3:
                menuViewPort.enterOptionsView();
                activeController = optionsController;
            case 4:
                menuViewPort.enterExitGameView();
                activeController = exitGameController;
                break;
        }
    }

}
