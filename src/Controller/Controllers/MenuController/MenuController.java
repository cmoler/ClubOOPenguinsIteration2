package Controller.Controllers.MenuController;

import Controller.Controllers.Controller;
import View.MenuView.MainMenuView;
import View.MenuView.MenuViewPort;

public abstract class MenuController implements Controller {

    protected int selectedUpDown = 0;
    protected int selectedRightLeft = 0;

    protected MenuViewPort menuViewPort;

    public abstract void select();

    protected abstract void correctUpDownParameters();
    protected abstract void correctLeftRightParameters();

    protected void setMenuViewPort(MenuViewPort menuViewPort){
        this.menuViewPort = menuViewPort;
    }

    public void scrollUp(){
        selectedUpDown -= 1;
        correctParametersAndUpdateView();
    }

    public void scrollDown(){
        selectedUpDown += 1;
        correctParametersAndUpdateView();
    }

    public void scrollLeft(){
        selectedRightLeft -= 1;
        correctParametersAndUpdateView();
    }

    public void scrollRight(){
        selectedRightLeft += 1;
        correctParametersAndUpdateView();
    }

    private void correctParametersAndUpdateView(){
        correctUpDownParameters();
        correctLeftRightParameters();
        menuViewPort.setSelectedX(selectedRightLeft);
        menuViewPort.setSelectedY(selectedUpDown);
    }

    @Override
    public void setActive() {
        menuViewPort.setVisible(true);
        menuViewPort.requestFocus();
    }
}
