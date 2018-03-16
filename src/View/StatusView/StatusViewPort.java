package View.StatusView;


import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Role.Role;
import View.Viewport;

import java.awt.*;


public class StatusViewPort extends Viewport {


    private int currentView = 2;

    public StatusViewPort(Entity entity, Equipment equipment, Inventory inventory, Role role){
        add(new StatsView(entity));
        add(new EquipmentView(equipment));
        add(new InventoryView(inventory));
        add(new SkillsView(role));
    }

    @Override
    public void draw(Graphics2D graphics2D){
        children.get(0).draw(graphics2D);
        switch (currentView){
            case 1:
                children.get(1).draw(graphics2D);
                break;
            case 2:
                children.get(2).draw(graphics2D);
                break;
            case 3:
                children.get(3).draw(graphics2D);
                break;
        }
    }

    public void switchToEquipment(){
        currentView = 1;
    }

    public void switchToInventory(){
        currentView = 2;
    }

    public void switchToSkills(){
        currentView = 3;
    }

}
