package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameBuilder;
import View.MenuView.NewGameView;

public class NewGameController extends MenuController {

    private NewGameView newGameView;
    private MainMenuController parent;
    GameBuilder gameBuilder;

    public NewGameController(GameBuilder gameBuilder, MainMenuController parent) {
        newGameView = gameBuilder.getMainMenuViewport().getNewGameView();
        this.gameBuilder = gameBuilder;
        setMenuViewPort(newGameView);
        this.parent = parent;
    }

    @Override
    public void select() {
        switch(verticalSelection){
            case 0: gameBuilder.getMemorySlots().loadDefaultGameSmasher(); break;
            case 1: gameBuilder.getMemorySlots().loadDefaultGameSummoner(); break;
            case 2: gameBuilder.getMemorySlots().loadDefaultGameSneak(); break;

        }
    }

    protected void correctUpDownParameters() {
        if(verticalSelection < 0) verticalSelection = 2;
        else if(verticalSelection > 2) verticalSelection = 0;
    }

    protected void correctLeftRightParameters() {

    }

}
