package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import Controller.SavingLoading.KeyBindings;
import View.Viewport;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class OptionsView extends MenuViewPort {

    private KeyBindings keyBindings;
    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;

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
            graphics2D.drawString(KeyEvent.getKeyText(keyBindings.getKey(keyBindings.getBinding(selectedX), i).getValue()), startX + sizeOfKeyBoxX / 2, (startY + sizeOfKeyBoxY * i + sizeOfKeyBoxY / 2));
        }

        int selectionBoxY = startY + selectedY * sizeOfKeyBoxY;

        graphics2D.drawImage(selected, startX, selectionBoxY, sizeOfKeyBoxX, sizeOfKeyBoxY, this);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
