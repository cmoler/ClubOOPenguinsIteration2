package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import View.MenuView.NewGameView;

public class NewGameController extends MenuController {

    private NewGameView newGameView;
    GameLoader gameLoader;

    public NewGameController(GameLoader gameLoader, MainMenuController parent) {
        newGameView = gameLoader.getMainMenuViewport().getNewGameView();
        this.gameLoader = gameLoader;
        setMenuViewPort(newGameView);
    }

    public void select(){
        String fileName = Commons.SAVE_FOLDER + Commons.SAVE_NAME + Commons.DEFAULT_SAVE;
        gameLoader.loadGame(fileName);
    }

    protected void correctUpDownParameters() {
        if(verticalSelection < 0) verticalSelection = 2;
        else if(verticalSelection > 2) verticalSelection = 0;
    }

    protected void correctLeftRightParameters() {
        if(horizontalSelection < 0) horizontalSelection = 1;
        else if(horizontalSelection > 1) horizontalSelection = 0;
    }

}
