package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.ExitGameView;

public class ExitGameController extends MenuController {

    private int currentlySelected = 1;

    private ExitGameView exitGameView;

    public ExitGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        exitGameView = gameLoader.getMenuViewport().getExitGameView();
    }

    public void scrollDown(){
        currentlySelected = -1;
        exitGameView.setSelectedMenuView(currentlySelected);
    }
}
