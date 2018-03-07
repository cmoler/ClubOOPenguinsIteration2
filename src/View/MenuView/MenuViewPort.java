package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import View.Viewport;

import java.awt.*;


public class MenuViewPort extends Viewport {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int selectedX = Configs.Commons.SCREEN_WIDTH/2;
    private int selectedXz = 0;
    private int selectedY = Commons.SCREEN_HEIGHT/4;
    private int selectedYz = 0;

    public MenuViewPort(){
        int startX = Configs.Commons.SCREEN_WIDTH/2;
        int startY = Commons.SCREEN_HEIGHT/4;

        add(new NewGameView(startX, startY));
        add(new SaveGameView(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT));
        add(new LoadGameView(startX, startY + 2*TextBoxInfo.TEXTBOX_HEIGHT));
        add(new ExitGameView(startX, startY + 3*TextBoxInfo.TEXTBOX_HEIGHT));
        add(new AvatarSelectionView(0, 0));
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.drawImage(selected, selectedX, selectedY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
        graphics2D.drawImage(selected, selectedXz, selectedYz, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
        super.draw(graphics2D);
    }

    @Override
    public void setSelectedMenuView(int selectedMenuView){
        if(selectedMenuView == -1 && selectedY < Commons.SCREEN_HEIGHT/4 + 3*TextBoxInfo.TEXTBOX_HEIGHT)
            this.selectedY += TextBoxInfo.TEXTBOX_HEIGHT;
        else if(selectedMenuView == 1 && selectedY > Commons.SCREEN_HEIGHT/4)
                this.selectedY -= TextBoxInfo.TEXTBOX_HEIGHT;
    }

    public void setSelectedAvatarView(int selectedAvatarView){
        if(selectedAvatarView == 1){
            this.selectedXz = TextBoxInfo.TEXTBOX_WIDTH;
        }
        else {
            this.selectedXz = 0;
        }
    }
}
