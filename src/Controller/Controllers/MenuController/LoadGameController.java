package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.MemorySlots;
import View.MenuView.LoadGameView;

public class LoadGameController extends MainMenuController {


    private LoadGameView loadGameView;
    GameBuilder gameBuilder;
    MemorySlots memorySlots;
    MainMenuController parent;

    public LoadGameController(GameBuilder gameBuilder, MainMenuController parent) {
        this.gameBuilder = gameBuilder;
        this.memorySlots = gameBuilder.getMemorySlots();
        loadGameView = gameBuilder.getMainMenuViewport().getLoadGameView();
        setMenuViewPort(loadGameView);
        loadGameView.setSlots(memorySlots);
        this.parent = parent;

    }

    @Override
    public void scrollUp(){
        memorySlots.selectPrevious();
    }

    @Override
    public void scrollDown(){
        memorySlots.selectNext();
    }

    public void select(){
        memorySlots.loadOnSelected();
        parent.loadGame();
    }

}
