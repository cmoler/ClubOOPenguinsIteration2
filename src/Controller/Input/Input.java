package Controller.Input;

import Controller.States.ControllerState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private ControllerState controllerState;

    public Input(ControllerState controllerState){
        this.controllerState = controllerState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        controllerState.process(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
