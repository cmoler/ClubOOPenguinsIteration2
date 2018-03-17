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

                Image itemImage = SpriteParser.getSpriteParser().getItemFromName(inventoryIterator.getCurrent().getName());
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

    @Override
    public void update(){
        highlighterX = inventory.getSelectedX();
        highlighterY = inventory.getSelectedY();
    }

}