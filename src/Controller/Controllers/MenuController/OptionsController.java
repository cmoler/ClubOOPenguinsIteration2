package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameLoader;
import View.MenuView.OptionsView;

public class OptionsController extends MenuController {

    private int currentlySelected = 1;

    private OptionsView optionsView;

    public OptionsController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        optionsView = gameLoader.getMenuViewport().getOptionsView();
    }

    public void scrollDown(){
        currentlySelected = -1;
        optionsView.setSelectedMenuView(currentlySelected);
    }

}
