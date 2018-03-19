package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Item.TakeableItem.TakeableItem;
import View.AreaView.AreaViewPort;
import View.Viewport;

public class InventoryController implements Controller{

    private Inventory inventory;
    private Equipment equipment;
    private Viewport areaViewPort;

    public InventoryController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getViewport();
        this.inventory = gameBuilder.getInventory();
        this.equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {
        areaViewPort.setVisible(true);
        areaViewPort.requestFocus();
    }

    public void equipItem() {
        TakeableItem item = inventory.removeItem(inventory.getSelectedIndex());
        equipment.equip(item);
    }

    public void scrollLeft() {
        inventory.scrollHorizontal(-1);
    }

    public void scrollRight() {
        inventory.scrollHorizontal(1);
    }

    public void scrollUp() {
        inventory.scrollVeritical(-1);
    }

    public void scrollDown() {
        inventory.scrollVeritical(1);
    }
}
