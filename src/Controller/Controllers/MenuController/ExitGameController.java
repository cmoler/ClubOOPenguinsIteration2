package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameLoader;
import View.MenuView.ExitGameView;

public class ExitGameController extends MenuController {

    private MainMenuController mainMenuController;

    public ExitGameController(GameLoader gameLoader, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        setMenuViewPort(gameLoader.getMainMenuViewport().getExitGameView());
    }

    @Override
    public void select() {
        if(selectedRightLeft == 0) mainMenuController.exitSubMenu();
        else if(selectedRightLeft == 1) System.exit(0);
    }

    protected void correctUpDownParameters() {
        if(selectedUpDown != 0) selectedUpDown = 0;
    }

    protected void correctLeftRightParameters() {
        if(selectedRightLeft < 0) selectedRightLeft = 0;
        else if(selectedRightLeft > 1) selectedRightLeft = 1;
    }

}
