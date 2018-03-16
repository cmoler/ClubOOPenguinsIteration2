package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameBuilder;

public class ExitGameController extends MenuController {

    private MainMenuController mainMenuController;

    public ExitGameController(GameBuilder gameBuilder, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        setMenuViewPort(gameBuilder.getMainMenuViewport().getExitGameView());
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
