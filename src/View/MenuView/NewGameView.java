package View.MenuView;

import Configs.Commons;
import Configs.SpriteParser;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class NewGameView extends MenuViewPort {

    private int selectedX = Configs.Commons.SCREEN_WIDTH/2;
    private int selectedY = Commons.SCREEN_HEIGHT/4;
    private int selectedXz = 0;
    private int selectedYz = 0;


    public NewGameView(){

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int startX = Configs.Commons.SCREEN_WIDTH/2;
        int startY = Commons.SCREEN_HEIGHT/4;

        graphics2D.drawString("Select Your Avatar", (startX), (startY));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage(), startX, startY+ TextBoxInfo.TEXTBOX_HEIGHT,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("1",startX,startY + TextBoxInfo.TEXTBOX_HEIGHT);

        graphics2D.drawRect(startX + TextBoxInfo.TEXTBOX_WIDTH, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage_NINJA(), startX + TextBoxInfo.TEXTBOX_WIDTH, startY + TextBoxInfo.TEXTBOX_HEIGHT,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("2",startX + TextBoxInfo.TEXTBOX_WIDTH,startY + TextBoxInfo.TEXTBOX_HEIGHT);

        graphics2D.drawString("Select Your Class", (startX), (startY + 3 * TextBoxInfo.TEXTBOX_HEIGHT));

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
