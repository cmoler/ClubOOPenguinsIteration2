package Controller.Input;

import Controller.States.ControllerState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private ControllerState activeState;

    public Input(ControllerState controllerState){
        this.activeState = controllerState;
    }

    public void setActiveState(ControllerState controllerState){
        this.activeState = controllerState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Typed " + e.getKeyCode());
        //controllerState.process(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        activeState.process(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
