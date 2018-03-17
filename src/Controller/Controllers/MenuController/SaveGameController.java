package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.MemorySlots;
import View.MenuView.SaveGameView;

public class SaveGameController extends MainMenuController {

    private SaveGameView saveGameView;
    private GameBuilder gameBuilder;
    MemorySlots memorySlots;

    public SaveGameController(GameBuilder gameBuilder, MainMenuController parent) {
        saveGameView = gameBuilder.getMainMenuViewport().getSaveGameView();
        this.gameBuilder = gameBuilder;
        this.memorySlots = gameBuilder.getMemorySlots();
        setMenuViewPort(saveGameView);
        saveGameView.setSlots(memorySlots);
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
