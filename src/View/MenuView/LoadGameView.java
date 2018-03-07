package View.MenuView;

import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class LoadGameView extends Viewport {

    private int x;
    private int y;

    public LoadGameView(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(x, y, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Load Game", (x), (y+TextBoxInfo.TEXTBOX_HEIGHT/4));
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
