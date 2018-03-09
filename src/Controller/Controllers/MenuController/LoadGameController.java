package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;

public class LoadGameController extends MenuController {

    public LoadGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
    }
}
