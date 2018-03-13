package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class ExitGameView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int selectedX = 0;

    public ExitGameView(){

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int startX = Configs.Commons.SCREEN_WIDTH/2;
        int startY = Commons.SCREEN_HEIGHT/4;

        graphics2D.drawString("Are you Sure, unsaved changes wont be saved", (startX), (startY));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("No",startX, startY + TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4);

        graphics2D.drawRect(startX + TextBoxInfo.TEXTBOX_WIDTH, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Exit Game", (startX + TextBoxInfo.TEXTBOX_WIDTH), (startY + TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        int selectedXPos = startX + TextBoxInfo.TEXTBOX_HEIGHT * selectedX;
        int selectedYPos = startY + TextBoxInfo.TEXTBOX_HEIGHT;

        graphics2D.drawImage(selected, selectedXPos, selectedYPos, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
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
        if(selectedX > 0)selectedX--;
    }

    @Override
    public void scrollRight() {
        if(selectedX < 1) selectedX++;
    }
}
