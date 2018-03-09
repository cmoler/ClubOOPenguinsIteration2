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

    int currentSubMenu = -1;

    public MenuViewPort(){
        add(new NewGameView());
        add(new SaveGameView());
        add(new LoadGameView());
        add(new ExitGameView());
        add(new AvatarSelectionView(0, 0));
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (currentSubMenu){
            case -1:
                drawMainMenu(graphics2D);
                break;
            case 0:
                children.get(0).draw(graphics2D);
                break;
            case 1:
                children.get(1).draw(graphics2D);
                break;
            case 2:
                children.get(2).draw(graphics2D);
                break;
            case 3:
                children.get(3).draw(graphics2D);
            break;
        }

    }

    private void drawMainMenu(Graphics2D graphics2D){
        int startX = Configs.Commons.SCREEN_WIDTH/2;
        int startY = Commons.SCREEN_HEIGHT/4;

        graphics2D.drawRect(startX, startY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("New Game", (startX), (startY+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save Game", (startX), (startY + TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 2*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Load Game", (startX), (startY + 2*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 3*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Exit Game", (startX), (startY + 3*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawImage(selected, selectedX, selectedY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
        graphics2D.drawImage(selected, selectedXz, selectedYz, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
    }

    public void setSelectedMenuView(int selectedMenuView){
        if(selectedMenuView == -1 && selectedY < Commons.SCREEN_HEIGHT/4 + 3*TextBoxInfo.TEXTBOX_HEIGHT)
            this.selectedY += TextBoxInfo.TEXTBOX_HEIGHT;
        else if(selectedMenuView == 1 && selectedY > Commons.SCREEN_HEIGHT/4)
                this.selectedY -= TextBoxInfo.TEXTBOX_HEIGHT;
    }

    public void enterNewGameView(){
        currentSubMenu = 0;
    }

    public SaveGameView getSaveGameView(){
        return (SaveGameView)children.get(1);
    }

    public void enterSaveGameView(){
        currentSubMenu = 1;
    }

    public void enterLoadGameView(){
        currentSubMenu = 2;
    }

    public void enterExitGameView(){
        currentSubMenu = 3;
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
