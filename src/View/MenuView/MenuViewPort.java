package View.MenuView;

import View.Viewport;

import javax.swing.*;

public abstract class MenuViewPort extends Viewport {

    protected int selectedX;
    protected int selectedY;

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }
}