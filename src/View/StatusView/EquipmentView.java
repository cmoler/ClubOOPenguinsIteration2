package View.StatusView;

import Configs.Commons;
import Configs.EquipmentSizes;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Entity.Equipment;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;
import javafx.util.Pair;

import java.awt.*;
import java.util.List;

public class EquipmentView extends Viewport {

    private final int EQUIPMENT_X = (int) (Commons.SCREEN_WIDTH * 660.0/765.0);
    private final int EQUIPMENT_Y = (int) (Commons.SCREEN_HEIGHT * 250.0/501.0);

    private Equipment equipment;
    private String head = null;
    private String body = null;
    private String legs = null;
    private String ring = null;
    private Image selectedImage = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private Pair<Integer, Integer> selectedArmor;

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
        selectedArmor = equipment.getSelectedArmor();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(head != null) drawHelmet(graphics2D);
        if(body != null) drawBody(graphics2D);
        if(legs != null) drawLegs(graphics2D);
        if(ring != null) drawRing(graphics2D);

        if(selectedArmor.getKey() > 0){
            // if were to the right and over the ring slot
            int selectedX = EQUIPMENT_X + EquipmentSizes.EQUIPMENT_WIDTH;
            int selectedY = EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT;

            graphics2D.drawImage(selectedImage, selectedX, selectedY,
                    EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
        }
        else{
            // over the head/body/legs
            int selectedX = EQUIPMENT_X;
            int selectedY = EquipmentSizes.EQUIPMENT_WIDTH * selectedArmor.getValue();

            graphics2D.drawImage(selectedImage, selectedX, selectedY,
                    EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
        }
    }

    private void drawHelmet(Graphics2D graphics2D) {
        int helmetX = EQUIPMENT_X;
        int helmetY = EQUIPMENT_Y;

        Image image = SpriteParser.getSpriteParser().getItemFromName(head);

        graphics2D.drawImage(image, helmetX, helmetY, EquipmentSizes.EQUIPMENT_WIDTH, EquipmentSizes.EQUIPMENT_HEIGHT, this);
    }

    private void drawBody(Graphics2D graphics2D) {
        int bodyX = EQUIPMENT_X;
        int bodyY = EQUIPMENT_Y + EquipmentSizes.EQUIPMENT_HEIGHT;

        Image image = SpriteParser.getSpriteParser().getItemFromName(body);

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
        selectedArmor = equipment.getSelectedArmor();
    }
}
