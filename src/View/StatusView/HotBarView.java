package View.StatusView;

import Configs.Commons;
import Configs.HotBarSizes;
import Configs.SpriteParser;
import Model.Entity.Equipment;
import View.Viewport;

import java.awt.*;

public class HotBarView extends Viewport {

    private final int HOT_BAR_X = (int) (Commons.SCREEN_WIDTH * 660.0/765.0);
    private final int HOT_BAR_Y = (int) (Commons.SCREEN_HEIGHT * 250.0/501.0);

    private Equipment equipment;

    public HotBarView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
    }


    @Override
    public void draw(Graphics2D graphics2D){
        int numberOfSlots = equipment.getEquipmentSize();

        for(int i = 0; i < numberOfSlots; ++i){
            //draw square
            graphics2D.drawRect(HOT_BAR_X * i, HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT);
            //draw image for item
            Image itemImage = SpriteParser.getSpriteParser().getItemFromName(equipment.getSlot(i).getName());
            graphics2D.drawImage(itemImage, HOT_BAR_X + i * HotBarSizes.HOT_BAR_WIDTH, HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
            graphics2D.drawString(Integer.toString(i), HOT_BAR_X + i * HotBarSizes.HOT_BAR_WIDTH, HOT_BAR_Y);
        }
    }

}
