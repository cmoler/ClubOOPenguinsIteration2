package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;

public class ExitGameController extends MenuController {

    public ExitGameController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
    }
}
