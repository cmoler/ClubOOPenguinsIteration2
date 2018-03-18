package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;
import View.AreaView.AreaViewPort;

public class EquipmentController implements Controller{

    private Equipment equipment;
    private AreaViewPort areaViewPort;

    public EquipmentController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getAreaViewport();
        this.equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {
        areaViewPort.setVisible(true);
        areaViewPort.requestFocus();
    }

    public void unEquipUsableItem(int indexOfItemInInventory){
        equipment.unEquipUsableItem(indexOfItemInInventory);
    }

    public void unEquipWearableItem(String armorType){
        equipment.unEquipWearableItem(armorType);
    }

}
