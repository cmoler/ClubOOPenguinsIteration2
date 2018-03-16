package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameLoader;

public class ExitGameController extends MenuController {

    private MainMenuController mainMenuController;

    public ExitGameController(GameLoader gameLoader, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        setMenuViewPort(gameLoader.getMainMenuViewport().getExitGameView());
    }

    @Override
    public void select() {
        if(horizontalSelection == 0) mainMenuController.exitSubMenu();
        else if(horizontalSelection == 1) System.exit(0);
    }

    protected void correctUpDownParameters() {
        if(verticalSelection != 0) verticalSelection = 0;
    }

    protected void correctLeftRightParameters() {
        if(horizontalSelection < 0) horizontalSelection = 1;
        else if(horizontalSelection > 1) horizontalSelection = 0;
    }

}
