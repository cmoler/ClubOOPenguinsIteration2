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

    public void scrollUp(){
        if(currentlySelected > 0) currentlySelected -= 1;
        newGameView.setSelectedMenuView(1);
    }

    public void scrollDown(){
        if(currentlySelected < 4) currentlySelected += 1;
        newGameView.setSelectedMenuView(-1);
    }
}