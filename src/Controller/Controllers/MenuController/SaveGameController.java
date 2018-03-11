package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import Controller.SavingLoading.GameSaver;
import View.MenuView.SaveGameView;

public class SaveGameController extends MenuController {

    private int currentlySelected = 1;

    private SaveGameView saveGameView;
    private GameLoader gameLoader;

    public SaveGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        saveGameView = gameLoader.getMenuViewport().getSaveGameView();
        this.gameLoader = gameLoader;
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
