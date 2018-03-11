package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameLoader;
import View.MenuView.ExitGameView;

public class ExitGameController extends MenuController {

    private int currentlySelected = 1;

    private ExitGameView exitGameView;
    private MainMenuController mainMenuController;

    public ExitGameController(GameLoader gameLoader, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        setMenuViewPort(gameLoader.getMainMenuViewport().getExitGameView());
    }

    @Override
    public void select() {

    }

    @Override
    protected void correctUpDownParameters() {

    }

    @Override
    protected void correctLeftRightParameters() {

    }

}
