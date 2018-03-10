package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.SaveGameView;

public class SaveGameController extends MenuController {

    private int currentlySelected = 1;

    private SaveGameView saveGameView;

    public SaveGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        saveGameView = gameLoader.getMenuViewport().getSaveGameView();
    }

    public void scrollDown(){
        currentlySelected = -1;
        saveGameView.setSelectedMenuView(currentlySelected);
    }
}
