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

    public void scrollUp(){
        if(currentlySelected > 0) currentlySelected -= 1;
        optionsView.setSelectedMenuView(1);
    }

    public void scrollDown(){
        if(currentlySelected < 4) currentlySelected += 1;
        optionsView.setSelectedMenuView(-1);
    }

}
