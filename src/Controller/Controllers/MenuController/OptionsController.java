package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import Controller.SavingLoading.KeyBindings;
import View.MenuView.OptionsView;

public class OptionsController extends MenuController {

    private OptionsView optionsView;

    private KeyBindings keyBindings;

    private ControllerMediator controllerMediator;

    public OptionsController(GameLoader gameLoader, ControllerMediator controllerMediator) {
        optionsView = gameLoader.getMainMenuViewport().getOptionsView();
        setMenuViewPort(optionsView);
        this.controllerMediator = controllerMediator;

        this.keyBindings = gameLoader.getKeyBindings();
        optionsView.setKeyBindings(keyBindings);
    }

    public void select(){
        changeKeyBindings(keyBindings.getBinding(horizontalSelection),
                keyBindings.getKey(keyBindings.getBinding(horizontalSelection), verticalSelection).getKey());

    }

    protected void correctUpDownParameters() {
        if(verticalSelection > keyBindings.getNumberOfKeysForBinding(horizontalSelection) - 1) verticalSelection = 0;
        if(verticalSelection < 0) verticalSelection = keyBindings.getNumberOfKeysForBinding(horizontalSelection) - 1;
    }


    protected void correctLeftRightParameters() {
        if(horizontalSelection > keyBindings.getNumberOfBindings() - 1) horizontalSelection = 0;
        if(horizontalSelection < 0) horizontalSelection = keyBindings.getNumberOfBindings() - 1;
    }

    private void changeKeyBindings(String bindingToChange, String keyToChange){
        controllerMediator.primeKeyBindingState(bindingToChange, keyToChange);
        controllerMediator.changeToKeyBindingState();
    }
}
