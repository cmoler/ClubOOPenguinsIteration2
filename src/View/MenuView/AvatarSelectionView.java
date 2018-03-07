package View.MenuView;

import Configs.SpriteParser;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class AvatarSelectionView extends Viewport {
    private int x;
    private int y;

    public AvatarSelectionView(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.drawRect(x, y, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage(), x, y,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("1",x,y + TextBoxInfo.TEXTBOX_HEIGHT);

        graphics2D.drawRect(x + TextBoxInfo.TEXTBOX_WIDTH, y, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage_NINJA(), x + TextBoxInfo.TEXTBOX_WIDTH, y,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("2",x + TextBoxInfo.TEXTBOX_WIDTH,y + TextBoxInfo.TEXTBOX_HEIGHT);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
