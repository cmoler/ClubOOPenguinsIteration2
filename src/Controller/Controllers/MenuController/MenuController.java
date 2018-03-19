package Controller.Controllers.MenuController;

import Controller.Controllers.Controller;
import View.AreaView.AreaViewPort;
import View.MenuView.MenuViewPort;

public abstract class MenuController implements Controller {

    protected int verticalSelection = 0;
    protected int horizontalSelection = 0;

    protected MenuViewPort menuViewPort;
    protected AreaViewPort areaViewPort;

    public abstract void select();

    protected abstract void correctUpDownParameters();
    protected abstract void correctLeftRightParameters();

    protected void setMenuViewPort(MenuViewPort menuViewPort){
        this.menuViewPort = menuViewPort;
    }

    public void scrollUp(){
        verticalSelection -= 1;
        correctParametersAndUpdateView();
    }

    public void scrollDown(){
        verticalSelection += 1;
        correctParametersAndUpdateView();
    }

    public void scrollLeft(){
        horizontalSelection -= 1;
        correctParametersAndUpdateView();
    }

    public void scrollRight(){
        horizontalSelection += 1;
        correctParametersAndUpdateView();
    }

    private void correctParametersAndUpdateView(){
        correctUpDownParameters();
        correctLeftRightParameters();
        menuViewPort.setSelectedX(horizontalSelection);
        menuViewPort.setSelectedY(verticalSelection);
    }

    @Override
    public void setActive() {
        //areaViewPort.setVisible(false);
    }
}
