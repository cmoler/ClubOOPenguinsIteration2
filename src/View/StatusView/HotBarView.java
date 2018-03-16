package View.StatusView;

import Configs.SpriteParser;
import Model.Entity.Equipment;
import View.Viewport;

import java.awt.*;

public class HotBarView extends Viewport {

    private Equipment equipment;

    public HotBarView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
    }


    @Override
    public void draw(Graphics2D graphics2D){
        int numberOfSlots = equipment.getEquipmentSize();
        int boxWidth = 10;
        int boxHeight = 10;

        int startX = 0;
        int startY = 0;
        for(int i = 0; i < numberOfSlots; ++i){
            //draw square
            graphics2D.drawRect(startX * i, startY, boxWidth, boxHeight);
            //draw image for item
            Image itemImage = SpriteParser.getSpriteParser().getItemFromName(equipment.getSlot(i).getName());
            graphics2D.drawImage(itemImage, startX * i, startY, boxWidth, boxHeight, this);
            graphics2D.drawString(Integer.toString(i), startX, startY);
        }
    }

}
