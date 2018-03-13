package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.MainMenuView;

public class MainMenuController extends MenuController{

    private ControllerMediator controllerMediator;

    private MainMenuView mainMenuView;

    private MenuController activeSubMenu = null;
    private NewGameController newGameController;
    private SaveGameController saveGameController;
    private LoadGameController loadGameController;
    private OptionsController optionsController;
    private ExitGameController exitGameController;

    public MainMenuController(){

    }

    public MainMenuController(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.mainMenuView = gameLoader.getMainMenuViewport();
        setMenuViewPort(this.mainMenuView);
        this.controllerMediator = controllerMediator;

        newGameController = new NewGameController(gameLoader, this);
        saveGameController = new SaveGameController(gameLoader, this);
        loadGameController = new LoadGameController(gameLoader, this);
        optionsController = new OptionsController(gameLoader, controllerMediator);
        exitGameController = new ExitGameController(gameLoader, this);
    }

    public void exitSubMenu(){
        System.out.println("Exit menu");
        mainMenuView.returnToMenu();
        this.activeSubMenu = null;
    }

    @Override
    public void select() {
        if(activeSubMenu != null) {
            activeSubMenu.select();
            return;
        }
        switch (selectedUpDown){
            case 0:
                mainMenuView.enterNewGameView();
                activeSubMenu = newGameController;
                break;
            case 1:
                mainMenuView.enterSaveGameView();
                activeSubMenu = saveGameController;
                break;
            case 2:
                mainMenuView.enterLoadGameView();
                activeSubMenu = loadGameController;
                break;
            case 3:
                mainMenuView.enterOptionsView();
                activeSubMenu = optionsController;
            case 4:
                mainMenuView.enterExitGameView();
                activeSubMenu = exitGameController;
                break;
        }
    }

}
