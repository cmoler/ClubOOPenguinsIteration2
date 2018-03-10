package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.NewGameView;

public class NewGameController extends MenuController {

    private int currentlySelected = 1;

    private NewGameView newGameView;

    public NewGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        newGameView = gameLoader.getMenuViewport().getNewGameView();
    }

    public void scrollDown(){
        currentlySelected = -1;
        newGameView.setSelectedMenuView(currentlySelected);
    }
}
