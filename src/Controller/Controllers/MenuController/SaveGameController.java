package Controller.Controllers.MenuController;

import Configs.Commons;
import Controller.SavingLoading.GameLoader;
import Controller.SavingLoading.MemorySlots;
import View.MenuView.SaveGameView;

public class SaveGameController extends MainMenuController {

    private SaveGameView saveGameView;
    private GameLoader gameLoader;
    MemorySlots memorySlots;

    public SaveGameController(GameLoader gameLoader, MainMenuController parent) {
        saveGameView = gameLoader.getMainMenuViewport().getSaveGameView();
        this.gameLoader = gameLoader;
        this.memorySlots = gameLoader.getMemorySlots();
        setMenuViewPort(saveGameView);
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
        memorySlots.saveOnSelected();
    }
}
