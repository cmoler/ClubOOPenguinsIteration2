package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;

public class EquipmentController implements Controller{

    public EquipmentController(GameBuilder gameBuilder){

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
