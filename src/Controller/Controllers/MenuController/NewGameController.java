package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import View.MenuView.NewGameView;

public class NewGameController extends MenuController {

    private int currentlySelected = 1;

    private NewGameView newGameView;
    GameLoader gameLoader;

    public NewGameController(GameLoader gameLoader, MainMenuController parent) {
        newGameView = gameLoader.getMainMenuViewport().getNewGameView();
        this.gameLoader = gameLoader;
        setMenuViewPort(newGameView);
    }

    public void select(){
        String fileName = Commons.saveFolder + Commons.saveName + Commons.defaultSave;
        gameLoader.loadGame(fileName);
    }

    @Override
    protected void correctUpDownParameters() {

    }

    @Override
    protected void correctLeftRightParameters() {

    }
}
