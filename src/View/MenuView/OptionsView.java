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
        int sizeOfBindingHeaderBoxY = (Commons.SCREEN_HEIGHT/16);

        for(int i = 0; i < numberOfBindings; ++i){
            graphics2D.drawRect(startX + sizeOfBindingHeaderBox * i, startY, sizeOfBindingHeaderBox, sizeOfBindingHeaderBoxY);
            graphics2D.drawString(keyBindings.getBinding(i), (startX + sizeOfBindingHeaderBox * i), (startY + sizeOfBindingHeaderBoxY / 2));
        }

        if(selectedX < 0) selectedX = 0;
        if(selectedX > numberOfBindings - 1) selectedX = numberOfBindings - 1;
        int selectionBoxX = startX + selectedX * sizeOfBindingHeaderBox;

        graphics2D.drawImage(selected, selectionBoxX, startY, sizeOfBindingHeaderBox, sizeOfBindingHeaderBoxY, this);
    }

    private void drawBody(Graphics2D graphics2D){
        int startX = Configs.Commons.SCREEN_WIDTH/4;
        int startY = Commons.SCREEN_HEIGHT/4;

        int numberOfKeys = keyBindings.getNumberOfKeysForBinding(selectedX);

        int sizeOfKeyBoxX = (Commons.SCREEN_WIDTH/2);
        int sizeOfKeyBoxY = (Commons.SCREEN_HEIGHT/2)/numberOfKeys;

        for(int i = 0; i < numberOfKeys; ++i){
            graphics2D.drawRect(startX , startY + sizeOfKeyBoxY * i, sizeOfKeyBoxX, sizeOfKeyBoxY);
            graphics2D.drawString(keyBindings.getKey(keyBindings.getBinding(selectedX), i).getKey(), startX, (startY + sizeOfKeyBoxY * i + sizeOfKeyBoxY / 2));
            graphics2D.drawString(keyBindings.getKey(keyBindings.getBinding(selectedX), i).getValue().toString(), startX + sizeOfKeyBoxX / 2, (startY + sizeOfKeyBoxY * i + sizeOfKeyBoxY / 2));
        }

        if(selectedY < 0) selectedY = 0;
        if(selectedY > numberOfKeys - 1) selectedY = numberOfKeys - 1;
        int selectionBoxY = startY + selectedY * sizeOfKeyBoxY;

        graphics2D.drawImage(selected, startX, selectionBoxY, sizeOfKeyBoxX, sizeOfKeyBoxY, this);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    @Override
    public void scrollUp() {
        this.selectedY--;
    }

    @Override
    public void scrollDown() {
        this.selectedY++;
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
