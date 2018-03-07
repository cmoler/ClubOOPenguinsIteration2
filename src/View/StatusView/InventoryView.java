package View.StatusView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.InventorySizes;
import Model.Entity.Inventory;
import View.Viewport;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Configs.InventorySizes.ITEM_HEIGHT;
import static Configs.InventorySizes.ITEM_WIDTH;

public class InventoryView extends Viewport {

    private Inventory inventory;
    private Image highlighter;
    private int highlighterX = ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0));
    private int highlighterY = ((int) (Commons.SCREEN_HEIGHT  * 221.0/765.0) + 170);
    private boolean selected = false;

    public InventoryView(Inventory inventory){
        inventory.attach(this);
        this.inventory = inventory;
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        Inventory.InventoryIterator inventoryIterator = inventory.getIterator();
        inventoryIterator.reset();
        for(int i = 0; i < InventorySizes.INVENTORY_ROWS && inventoryIterator.hasNext(); ++i, inventoryIterator.next()){
            for(int j = 0; j < InventorySizes.INVENTORY_COLUMNS && inventoryIterator.hasNext(); ++j, inventoryIterator.next()){

                ImageIcon imageIcon = new ImageIcon(ImagesInfo.ITEM_TAKEABLE_FILELOCATION);
                Image itemImage = imageIcon.getImage();
                int x = ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)) + ITEM_WIDTH*j;
                int y = ((int) (Commons.SCREEN_WIDTH  * 221.0/765.0)) + ITEM_HEIGHT*i;
                graphics2D.drawImage(itemImage, x, y, ITEM_WIDTH, ITEM_WIDTH, this);

            }
        }
        if(selected) {
            graphics2D.setColor(new Color(200, 200, 50, 90));
            graphics2D.fillRect(highlighterX, highlighterY, InventorySizes.ITEM_WIDTH, InventorySizes.ITEM_HEIGHT);
            graphics2D.fillRect(((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), ((int)(Commons.SCREEN_HEIGHT  * 221.0/765.0) + 170),
                    InventorySizes.ITEM_WIDTH * 5, InventorySizes.ITEM_HEIGHT * 5);
        }
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}