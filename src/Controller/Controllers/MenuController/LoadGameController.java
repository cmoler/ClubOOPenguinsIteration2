package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import View.MenuView.LoadGameView;

public class LoadGameController extends MenuController {

    private int currentlySelected = 1;

    private LoadGameView loadGameView;
    GameLoader gameLoader;

    public LoadGameController(GameLoader gameLoader, MainMenuController parent) {
        this.gameLoader = gameLoader;
        loadGameView = gameLoader.getMainMenuViewport().getLoadGameView();
        setMenuViewPort(loadGameView);
    }

    public void select(){
        String fileName = Commons.saveFolder + Commons.saveName + currentlySelected;
        gameLoader.loadGame(fileName);
    }

    @Override
    protected void correctUpDownParameters() {

    }

    @Override
    protected void correctLeftRightParameters() {

    }

}
