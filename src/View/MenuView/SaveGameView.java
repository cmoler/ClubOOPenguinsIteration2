package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class SaveGameView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int selectedX = Configs.Commons.SCREEN_WIDTH/2;
    private int selectedY = Commons.SCREEN_HEIGHT/4;

    public SaveGameView(){

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int startX = Configs.Commons.SCREEN_WIDTH/2;
        int startY = Commons.SCREEN_HEIGHT/4;

        graphics2D.drawRect(startX, startY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save 1", (startX), (startY+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save 2", (startX), (startY + TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 2*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save 3", (startX), (startY + 2*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 3*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save 4", (startX), (startY + 3*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawImage(selected, selectedX, selectedY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
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

    }

    @Override
    public void scrollRight() {

    }
}
