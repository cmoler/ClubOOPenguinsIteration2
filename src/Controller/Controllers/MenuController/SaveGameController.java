package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import View.MenuView.SaveGameView;

public class SaveGameController extends MainMenuController {

    private int currentlySelected = 1;

    private SaveGameView saveGameView;
    private GameLoader gameLoader;

    public SaveGameController(GameLoader gameLoader, MainMenuController parent) {
        saveGameView = gameLoader.getMainMenuViewport().getSaveGameView();
        this.gameLoader = gameLoader;
        setMenuViewPort(saveGameView);
    }

    public void select(){
        String fileName = Commons.saveFolder + Commons.saveName + currentlySelected;
        gameLoader.saveGame(fileName);
    }

    public void scrollUp(){
        if(currentlySelected > 0) currentlySelected -= 1;
        saveGameView.setSelectedMenuView(1);
    }

    public void scrollDown(){
        if(currentlySelected < 3) currentlySelected += 1;
        saveGameView.setSelectedMenuView(-1);
    }
}
