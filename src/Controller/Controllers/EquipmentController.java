package Controller.Controllers;

import Controller.SavingLoading.GameLoader;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Item.TakeableItem.TakeableItem;

public class EquipmentController implements Controller{

    private Inventory inventory;
    private Equipment equipment;

    public EquipmentController(GameLoader gameLoader){
        inventory = gameLoader.getInventory();
        equipment = gameLoader.getEquipment();
    }

    @Override
    public void setActive() {

    }

    public void unEquipUsableItem(int indexOfItemInInventory){
        equipment.unEquipUsableItem(indexOfItemInInventory);
    }

    public void unEquipWearableItem(String armorType){
        equipment.unEquipWearableItem(armorType);
    }

}
