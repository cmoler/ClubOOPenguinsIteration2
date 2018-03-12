package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import Controller.SavingLoading.KeyBindings;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class OptionsView extends MenuViewPort {

    private KeyBindings keyBindings;
    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int selectedX = 0;
    private int selectedY = 0;

    public OptionsView(){

    }

    public void setKeyBindings(KeyBindings keyBindings){
        this.keyBindings = keyBindings;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawHeader(graphics2D);
        drawBody(graphics2D);
    }

    private void drawHeader(Graphics2D graphics2D){
        int startX = Configs.Commons.SCREEN_WIDTH/4;
        int startY = Commons.SCREEN_HEIGHT/8;

        int numberOfBindings = keyBindings.getNumberOfBindings();

        int sizeOfBindingHeaderBox = (Commons.SCREEN_WIDTH/2)/numberOfBindings;
        int sizeOfBindingHeaderBoxY = (Commons.SCREEN_HEIGHT/3)/numberOfBindings;

        for(int i = 0; i < numberOfBindings; ++i){
            graphics2D.drawRect(startX + sizeOfBindingHeaderBox * i, startY, sizeOfBindingHeaderBox, sizeOfBindingHeaderBoxY);
            graphics2D.drawString(keyBindings.getBinding(i), (startX + sizeOfBindingHeaderBox * i), (startY + sizeOfBindingHeaderBoxY / 2));
        }


        int selectionBoxX = startX + selectedX * sizeOfBindingHeaderBox;
        int selectionBoxY = startY + selectedY * sizeOfBindingHeaderBox;

        graphics2D.drawImage(selected, selectionBoxX, selectionBoxY, sizeOfBindingHeaderBox, sizeOfBindingHeaderBoxY, this);
    }

    private void drawBody(Graphics2D graphics2D){

    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    @Override
    public void scrollUp() {

    }

    @Override
    public void scrollDown() {

    }

    @Override
    public void scrollLeft() {
        this.selectedX--;
    }

    @Override
    public void scrollRight() {
        this.selectedX++;
    }
}
