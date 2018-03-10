package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.LoadGameView;

public class LoadGameController extends MenuController {

    private int currentlySelected = 1;

    private LoadGameView loadGameView;

    public LoadGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        loadGameView = gameLoader.getMenuViewport().getLoadGameView();
    }

    public void scrollUp(){
        if(currentlySelected > 0) currentlySelected -= 1;
        loadGameView.setSelectedMenuView(1);
    }

    public void scrollDown(){
        if(currentlySelected < 3) currentlySelected += 1;
        loadGameView.setSelectedMenuView(-1);
    }
}
