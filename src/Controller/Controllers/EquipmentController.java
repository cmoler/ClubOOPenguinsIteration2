package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Equipment;

public class EquipmentController implements Controller{

    private Equipment equipment;

    public EquipmentController(GameBuilder gameBuilder){
        this.equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {

    }

    public void scrollLeft(){
        equipment.scrollLeft();
    }

    public void scrollRight(){
        equipment.scrollRight();
    }

    public void useItem(){
        equipment.useItem();
    }

    public void unEquip(){
        equipment.unEquip();
    }
}
