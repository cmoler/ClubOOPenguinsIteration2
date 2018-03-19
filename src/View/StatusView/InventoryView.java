package View.StatusView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.InventorySizes;
import Configs.SpriteParser;
import Model.Entity.Inventory;
import View.Viewport;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Configs.InventorySizes.ITEM_HEIGHT;
import static Configs.InventorySizes.ITEM_WIDTH;

public class InventoryView extends Viewport {

    private Inventory inventory;
    private Image selectedImage = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
    private int highlighterX = 0;
    private int highlighterY = 0;

    public InventoryView(Inventory inventory){
        inventory.attach(this);
        this.inventory = inventory;
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        int startX = ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0));
        int startY = ((int) (Commons.SCREEN_HEIGHT  * 221.0/765.0) + 170);

        Inventory.InventoryIterator inventoryIterator = inventory.getIterator();
        inventoryIterator.reset();
        for(int i = 0; i < InventorySizes.INVENTORY_ROWS && inventoryIterator.hasNext(); ++i, inventoryIterator.next()){
            for(int j = 0; j < InventorySizes.INVENTORY_COLUMNS && inventoryIterator.hasNext(); ++j, inventoryIterator.next()){

                Image itemImage = parseItem(inventoryIterator.getCurrent().getName());
                int x = (startX) + ITEM_WIDTH*j;
                int y = (startY) + ITEM_HEIGHT*i;
                graphics2D.drawImage(itemImage, x, y, ITEM_WIDTH, ITEM_WIDTH, this);
            }
        }

        int selectedX = startX + ITEM_WIDTH * highlighterX;
        int selectedY = startY + ITEM_HEIGHT * highlighterY;

        graphics2D.drawImage(selectedImage, selectedX, selectedY, ITEM_WIDTH, ITEM_HEIGHT, this);
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    private Image parseItem(String itemName){
        switch(itemName){
            case "body":
                return SpriteParser.getSpriteParser().getItemFromName("chest");
            case "head":
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

    @Override
    public void update(){
        highlighterX = inventory.getSelectedX();
        highlighterY = inventory.getSelectedY();
    }

}