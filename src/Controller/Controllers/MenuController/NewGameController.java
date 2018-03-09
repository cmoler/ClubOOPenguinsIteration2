package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;

public class NewGameController extends MenuController {

    public NewGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
    }
}
