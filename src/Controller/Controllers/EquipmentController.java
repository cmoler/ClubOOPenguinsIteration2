package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;

public class EquipmentController implements Controller{

    private Equipment equipment;

    public EquipmentController(GameBuilder gameBuilder){
        equipment = gameBuilder.getEquipment();
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
