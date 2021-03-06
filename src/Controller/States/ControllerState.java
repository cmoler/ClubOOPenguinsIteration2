package Controller.States;

import java.awt.event.KeyEvent;

public interface ControllerState {

    public void process(KeyEvent keyEvent);
    public void loadKeyBindings();
    public void setActive();
}
