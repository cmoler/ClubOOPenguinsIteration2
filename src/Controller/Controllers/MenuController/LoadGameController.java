package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import Controller.SavingLoading.MemorySlots;
import View.MenuView.LoadGameView;

public class LoadGameController extends MainMenuController {


    private LoadGameView loadGameView;
    GameLoader gameLoader;
    MemorySlots memorySlots;

    public LoadGameController(GameLoader gameLoader, MainMenuController parent) {
        this.gameLoader = gameLoader;
        this.memorySlots = gameLoader.getMemorySlots();
        loadGameView = gameLoader.getMainMenuViewport().getLoadGameView();
        setMenuViewPort(loadGameView);
        loadGameView.setSlots(memorySlots);

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
    }

}
