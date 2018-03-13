package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.Controllers.MenuController.MenuController;
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
        changeKeyBindings(keyBindings.getBinding(selectedRightLeft),
                keyBindings.getKey(keyBindings.getBinding(selectedRightLeft), selectedUpDown).getKey());

    }

    protected void correctUpDownParameters() {
        if(selectedUpDown > keyBindings.getNumberOfKeysForBinding(selectedRightLeft)) selectedUpDown = 0;
        if(selectedUpDown < 0) selectedUpDown = keyBindings.getNumberOfKeysForBinding(selectedRightLeft);
    }


    protected void correctLeftRightParameters() {
        if(selectedRightLeft > keyBindings.getNumberOfBindings()) selectedRightLeft = 0;
        if(selectedRightLeft < 0) selectedRightLeft = keyBindings.getNumberOfBindings() - 1;
    }

    private void changeKeyBindings(String bindingToChange, String keyToChange){
        controllerMediator.primeKeyBindingState(bindingToChange, keyToChange);
        controllerMediator.changeToKeyBindingState();
    }
}
