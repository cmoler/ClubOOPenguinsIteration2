package View.StatusView;

import Configs.*;
import Model.Entity.Equipment;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;
import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class EquipmentView extends Viewport {

    private final int EQUIPMENT_X = ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0));
    private final int EQUIPMENT_Y = ((int) (Commons.SCREEN_HEIGHT  * 221.0/765.0) + 170);
    private final int HOT_BAR_X = (int) (Commons.SCREEN_WIDTH * 170.0/765.0);
    private final int HOT_BAR_Y = (int) (Commons.SCREEN_HEIGHT * 310.0/501.0);

    private Equipment equipment;
    private String head = null;
    private String body = null;
    private String legs = null;
    private String ring = null;
    private Image selectedImage = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int selectedArmor;

    public EquipmentView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
        if(equipment.getHead() != null) head = equipment.getHead().getName();
        else head = null;
        if(equipment.getBody() != null) body = equipment.getBody().getName();
        else body = null;
        if(equipment.getLegs() != null) legs = equipment.getLegs().getName();
        else legs = null;
        if(equipment.getRing() != null) ring = equipment.getRing().getName();
        else ring = null;
        selectedArmor = equipment.getSelected();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(head != null) drawHelmet(graphics2D);
        if(body != null) drawBody(graphics2D);
        if(legs != null) drawLegs(graphics2D);
        if(ring != null) drawRing(graphics2D);

        switch (selectedArmor){
            case 0:
                graphics2D.drawImage(selectedImage, HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * 0), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
                break;
            case 1:
                graphics2D.drawImage(selectedImage, HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * 1), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
                break;
            case 2:
                graphics2D.drawImage(selectedImage, HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * 2), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
                break;
            case 3:
                graphics2D.drawImage(selectedImage, HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * 3), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
                break;
            case 4:
                graphics2D.drawImage(selectedImage, HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * 4), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
                break;
            case 5:
                graphics2D.drawImage(selectedImage, EQUIPMENT_X, EQUIPMENT_Y, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
                break;
            case 6:
                graphics2D.drawImage(selectedImage, EQUIPMENT_X, EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
                break;
            case 7:
                graphics2D.drawImage(selectedImage, EQUIPMENT_X, EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT * 2, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
                break;
            case 8:
                graphics2D.drawImage(selectedImage, EQUIPMENT_X + EquipmentSizes.EQUIPMENT_WIDTH, EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
                break;
        }
    }

    private void drawHelmet(Graphics2D graphics2D) {
        int helmetX = EQUIPMENT_X;
        int helmetY = EQUIPMENT_Y;

        Image image = SpriteParser.getSpriteParser().getItemFromName("helmet");

        graphics2D.drawImage(image, helmetX, helmetY, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
    }

    private void drawBody(Graphics2D graphics2D) {
        int bodyX = EQUIPMENT_X;
        int bodyY = EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT;

        Image image = SpriteParser.getSpriteParser().getItemFromName("chest");

        graphics2D.drawImage(image, bodyX, bodyY, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
    }

    private void drawLegs(Graphics2D graphics2D) {
        int legsX = EQUIPMENT_X;
        int legsY = EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT * 2;

        Image image = SpriteParser.getSpriteParser().getItemFromName(legs);

        graphics2D.drawImage(image, legsX, legsY, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
    }

    private void drawRing(Graphics2D graphics2D) {
        int ringX = EQUIPMENT_X + EquipmentSizes.EQUIPMENT_WIDTH;
        int ringY = EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT;

        Image image = SpriteParser.getSpriteParser().getItemFromName(ring);

        graphics2D.drawImage(image, ringX, ringY, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }


    @Override
    public void update(){
        if(equipment.getHead() != null) head = equipment.getHead().getName();
        else head = null;
        if(equipment.getBody() != null) body = equipment.getBody().getName();
        else body = null;
        if(equipment.getLegs() != null) legs = equipment.getLegs().getName();
        else legs = null;
        if(equipment.getRing() != null) ring = equipment.getRing().getSlot();
        else ring = null;
        selectedArmor = equipment.getSelected();
    }
}
