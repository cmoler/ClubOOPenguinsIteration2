package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import Controller.SavingLoading.MemorySlots;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class LoadGameView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private MemorySlots memorySlots;

    public LoadGameView(){

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int startX = Configs.Commons.SCREEN_WIDTH/2 - Commons.SCREEN_HEIGHT/8;
        int startY = Commons.SCREEN_HEIGHT/4;

        int selectedY = memorySlots.getSelectedSlot();

        int numberOfSaves = Commons.MAX_SAVE_SLOTS;

        int sizeOfSaveSlotX = (TextBoxInfo.TEXTBOX_WIDTH);
        int sizeOfSaveSlotY = (Commons.SCREEN_WIDTH/3)/numberOfSaves;

        for(int i = 0; i < numberOfSaves; ++i){
            graphics2D.drawRect(startX, startY + sizeOfSaveSlotY * i, sizeOfSaveSlotX, sizeOfSaveSlotY);
            graphics2D.drawString("Load "+(i+1), (startX), ( startY + sizeOfSaveSlotY * i+TextBoxInfo.TEXTBOX_HEIGHT/4));
        }

        int selectionBoxY = startY + selectedY * sizeOfSaveSlotY;

        graphics2D.drawImage(selected, startX, selectionBoxY, sizeOfSaveSlotX, sizeOfSaveSlotY, this);
    }

    public void setSlots(MemorySlots memorySlots){
        this.memorySlots = memorySlots;
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
