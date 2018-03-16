package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;

public class EquipmentController implements Controller{

    public EquipmentController(GameBuilder gameBuilder){

    }

    @Override
    public void setActive() {

    }

    public void equipItem(int indexOfItemInInventory){
        TakeableItem item = inventory.getItem(indexOfItemInInventory);
        equipment.equip(item);
    }

    public void unEquipUsableItem(int indexOfItemInInventory){
        equipment.unEquipUsableItem(indexOfItemInInventory);
    }

    public void unEquipWearableItem(String armorType){
        equipment.unEquipWearableItem(armorType);
    }

    public void useItem(int index){
        equipment.useItem(index);
    }
}
