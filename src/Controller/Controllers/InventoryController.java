package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Item.TakeableItem.TakeableItem;
import View.AreaView.AreaViewPort;

public class InventoryController implements Controller{

    private Inventory inventory;
    private Equipment equipment;
    private AreaViewPort areaViewPort;

    public InventoryController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getAreaViewport();
        inventory = gameBuilder.getInventory();
        equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {
        areaViewPort.setVisible(true);
        areaViewPort.requestFocus();
    }

    public void equipItem(int indexOfItemInInventory){
        TakeableItem item = inventory.getItem(indexOfItemInInventory);
        equipment.equip(item);
    }
}
