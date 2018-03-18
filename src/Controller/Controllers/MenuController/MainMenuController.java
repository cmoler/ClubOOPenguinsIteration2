package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameBuilder;
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

    public MainMenuController(GameBuilder gameBuilder, ControllerMediator controllerMediator){
        this.mainMenuView = gameBuilder.getMainMenuViewport();
        setMenuViewPort(this.mainMenuView);
        this.controllerMediator = controllerMediator;

        newGameController = new NewGameController(gameBuilder, this);
        saveGameController = new SaveGameController(gameBuilder, this);
        loadGameController = new LoadGameController(gameBuilder, this);
        optionsController = new OptionsController(gameBuilder, controllerMediator);
        exitGameController = new ExitGameController(gameBuilder, this);
    }

    public void exitSubMenu(){
        System.out.println("Exit menu");
        mainMenuView.returnToMenu();
        this.activeSubMenu = null;
    }

    protected void correctUpDownParameters() {
        if(verticalSelection < 0) verticalSelection = 4;
        else if(verticalSelection > 4) verticalSelection = 0;
    }

    protected void correctLeftRightParameters() {
        if(horizontalSelection != 0) horizontalSelection = 0;
    }

    @Override
    public void scrollUp(){
        if(activeSubMenu == null) super.scrollUp();
        else activeSubMenu.scrollUp();
    }

    @Override
    public void scrollDown(){
        if(activeSubMenu == null) super.scrollDown();
        else activeSubMenu.scrollDown();
    }

    @Override
    public void scrollLeft(){
        if(activeSubMenu == null) super.scrollLeft();
        else activeSubMenu.scrollLeft();
    }

    @Override
    public void scrollRight(){
        if(activeSubMenu == null) super.scrollRight();
        else activeSubMenu.scrollRight();
    }


    @Override
    public void select() {
        if(activeSubMenu != null) {
            activeSubMenu.select();
            return;
        }
        switch (verticalSelection){
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
                break;
            case 4:
                mainMenuView.enterExitGameView();
                activeSubMenu = exitGameController;
                break;
        }
    }

    public void loadGame() {
        controllerMediator.load();
    }
}
