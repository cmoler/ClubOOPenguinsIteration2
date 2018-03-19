package View.StatusView;

import Configs.Commons;
import Configs.HotBarSizes;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Entity.Equipment;
import View.Viewport;

import java.awt.*;

public class HotBarView extends Viewport {

    private final int HOT_BAR_X = (int) (Commons.SCREEN_WIDTH * 170.0/765.0);
    private final int HOT_BAR_Y = (int) (Commons.SCREEN_HEIGHT * 310.0/501.0);

    private Equipment equipment;

    public HotBarView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
    }


    @Override
    public void draw(Graphics2D graphics2D){
        int numberOfSlots = equipment.getEquipmentSize();
        graphics2D.setFont(new Font("Calibri",2,25));

        for(int i = 0; i < numberOfSlots; ++i){
            //draw square
            graphics2D.drawRect(HOT_BAR_X + (HotBarSizes.HOT_BAR_WIDTH * i), HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT);
            //draw image for item
            if (equipment.getSlot(i) != null){
                Image itemImage = parseItem(equipment.getSlot(i).getName());
                graphics2D.drawImage(itemImage, HOT_BAR_X + i * HotBarSizes.HOT_BAR_WIDTH, HOT_BAR_Y, HotBarSizes.HOT_BAR_WIDTH, HotBarSizes.HOT_BAR_HEIGHT, this);
            }
            graphics2D.drawString(Integer.toString(i+1), HOT_BAR_X + (i * HotBarSizes.HOT_BAR_WIDTH) + HotBarSizes.HOT_BAR_WIDTH/2 , HOT_BAR_Y);
        }
    }

    private Image parseItem(String itemName){
        switch(itemName){
            case "body":
                return SpriteParser.getSpriteParser().getItemFromName("chest");
            case "helmet":
                return SpriteParser.getSpriteParser().getItemFromName("helmet");
            case "legs":
                return SpriteParser.getSpriteParser().getItemFromName("legs");
            case "ring":
                return SpriteParser.getSpriteParser().getItemFromName("ring");
            case "angularIceAttack":
                return ImagesInfo.PROJECTILE_ANGULARICEATTACK;
            case "linearIceAttack":
                return ImagesInfo.PROJECTILE_LINEARICEATTACK;
            case "radialIceAttack":
                return ImagesInfo.PROJECTILE_LINEARICEATTACK;
            case "heal":
                return ImagesInfo.HEAL_IMAGE;
            case "increaseMaxHealth":
                return ImagesInfo.INCREASE_MAX_HEALTH_IMAGE;
            case "increaseLevel":
                return ImagesInfo.INCREASE_LEVEL_IMAGE;
            case "brassKnuckles":
                return SpriteParser.getSpriteParser().getItemFromName("brassKnuckles");
            case "spikedGloves":
                return SpriteParser.getSpriteParser().getItemFromName("spikedGloves");
            case "swordHands":
                return SpriteParser.getSpriteParser().getItemFromName("swordHands");
            case "charm":
                return ImagesInfo.CHARM_IMAGE;
            case "sleep":
                return ImagesInfo.SLEEP_IMAGE;
            case "seppuku":
                return ImagesInfo.SEPPUKU_IMAGE;
            case "key":
                return ImagesInfo.KEY_IMAGE;
            case "blueLightsaber":
                return SpriteParser.getSpriteParser().getItemFromName("blueLightsaber");
            case "mjolnir":
                return SpriteParser.getSpriteParser().getItemFromName("mjolnir");
            case "thunderBlade":
                return SpriteParser.getSpriteParser().getItemFromName("thunderBlade");
            case "pizza":
                return SpriteParser.getSpriteParser().getItemFromName("pizza");
            case "snowLauncher":
                return SpriteParser.getSpriteParser().getItemFromName("snowLauncher");
            case "snowShuriken":
                return SpriteParser.getSpriteParser().getItemFromName("snowShuriken");
            case "staffItem":
                return SpriteParser.getSpriteParser().getItemFromName("staff");
            case "inquisitorLightsaber":
                return SpriteParser.getSpriteParser().getItemFromName("inquisitorLightsaber");
            case "jeweledCutlass":
                return SpriteParser.getSpriteParser().getItemFromName("jeweledCutlass");
            case "waterHammer":
                return SpriteParser.getSpriteParser().getItemFromName("waterHammer");
            case "healthPotion":
                return ImagesInfo.HEALTH_POTION_IMAGE;
            case "manaPotion":
                return ImagesInfo.MANA_POTION_IMAGE;
            case "xpPotion":
                return ImagesInfo.EXP_POTION_IMAGE;
            case "chestInteractiveItemOpen":
                return ImagesInfo.CHEST_OPEN_IMAGE;
            case "chestInteractiveItemClosed":
                return ImagesInfo.CHEST_CLOSED_IMAGE;
            default:
                return SpriteParser.getSpriteParser().getItemFromName("chest");
        }
    }

}
