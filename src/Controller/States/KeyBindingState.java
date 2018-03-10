package Controller.States;

import Controller.ControllerMediator;

import java.awt.event.KeyEvent;

public class KeyBindingState implements ControllerState {

    private ControllerMediator controllerMediator;
    private String bindingToChange;
    private String keyToChange;

    public KeyBindingState(ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
    }

    @Override
    public void process(KeyEvent keyEvent) {

    }

    @Override
    public void loadKeyBindings() {

    }

    @Override
    public void setActive() {

    }

    public void setBindingToChange(String bindingToChange){
        this.bindingToChange = bindingToChange;
    }

    public void setKeyToChange(String keyToChange){
        this.keyToChange = keyToChange;
    }
}
