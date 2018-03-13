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
        if(selectedUpDown < 0) selectedUpDown = 2;
        else if(selectedUpDown > 2) selectedUpDown = 0;
    }

    protected void correctLeftRightParameters() {
        if(selectedRightLeft < 0) selectedRightLeft = 1;
        else if(selectedRightLeft > 1) selectedRightLeft = 0;
    }

}
