package View.StatusView;

import Configs.Commons;
import Configs.EquipmentSizes;
import Configs.SpriteParser;
import Model.Entity.Equipment;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class EquipmentView extends Viewport {

    private final int EQUIPMENT_X = (int) (Commons.SCREEN_WIDTH * 660.0/765.0);
    private final int EQUIPMENT_Y = (int) (Commons.SCREEN_HEIGHT * 250.0/501.0);

    private Equipment equipment;
    private boolean selected = false;

    public EquipmentView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
    }

//    @Override
//    public void draw(Graphics2D graphics2D) {
//        Image imageIcon = SpriteParser.getSpriteParser().getTakeAbleItemImage();
//        if(equipment.getEquipped() != null)
//        graphics2D.drawImage(imageIcon, EQUIPMENT_X, EQUIPMENT_Y, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
//        if(selected){
//            graphics2D.setColor(new Color(200, 200, 50, 90));
//            graphics2D.fillRect(EQUIPMENT_X, EQUIPMENT_Y, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT);
//        }
//    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
