package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Item.TakeableItem.TakeableItem;

public class InventoryController implements Controller{

    private Inventory inventory;
    private Equipment equipment;

    public InventoryController(GameBuilder gameBuilder){

        inventory = gameBuilder.getInventory();
        equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {

    }

    public void equipItem(int indexOfItemInInventory){
        TakeableItem item = inventory.getItem(indexOfItemInInventory);
        equipment.equip(item);
    }
}
