package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class NewGameView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;

    public NewGameView(){

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawAvatarSelection(graphics2D);
        drawClassSelection(graphics2D);
    }

    private void drawAvatarSelection(Graphics2D graphics2D){
        int startX = Configs.Commons.SCREEN_WIDTH/2 - TextBoxInfo.TEXTBOX_WIDTH;
        int startY = Commons.SCREEN_HEIGHT/8;

        graphics2D.drawString("Select Your Avatar", (startX), (startY + TextBoxInfo.TEXTBOX_HEIGHT/2));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage(), startX, startY+ TextBoxInfo.TEXTBOX_HEIGHT,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("1",startX,startY + TextBoxInfo.TEXTBOX_HEIGHT);

        graphics2D.drawRect(startX + TextBoxInfo.TEXTBOX_WIDTH, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawImage(SpriteParser.getSpriteParser().getAvatarImage_NINJA(), startX + TextBoxInfo.TEXTBOX_WIDTH, startY + TextBoxInfo.TEXTBOX_HEIGHT,TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT,this );
        graphics2D.drawString("2",startX + TextBoxInfo.TEXTBOX_WIDTH,startY + TextBoxInfo.TEXTBOX_HEIGHT);

        int selectionBoxX = startX + selectedX * TextBoxInfo.TEXTBOX_WIDTH;

        graphics2D.drawImage(selected, selectionBoxX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);

    }

    private void drawClassSelection(Graphics2D graphics2D){
        int startX = Configs.Commons.SCREEN_WIDTH/2 - TextBoxInfo.TEXTBOX_WIDTH;
        int startY = Commons.SCREEN_HEIGHT/3;

        graphics2D.drawString("Select Your Class", (startX), (startY + TextBoxInfo.TEXTBOX_HEIGHT/2));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Smasher",startX, startY + TextBoxInfo.TEXTBOX_HEIGHT + TextBoxInfo.TEXTBOX_HEIGHT / 2);

        graphics2D.drawRect(startX, startY + 2 * TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Summoner",startX,startY + TextBoxInfo.TEXTBOX_HEIGHT * 2 + TextBoxInfo.TEXTBOX_HEIGHT / 2);

        graphics2D.drawRect(startX, startY + 3 * TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Sneak",startX,startY + TextBoxInfo.TEXTBOX_HEIGHT * 3 + TextBoxInfo.TEXTBOX_HEIGHT / 2);

        int selectionBoxY = startY + TextBoxInfo.TEXTBOX_HEIGHT + selectedY * TextBoxInfo.TEXTBOX_WIDTH;

        graphics2D.drawImage(selected, startX, selectionBoxY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
