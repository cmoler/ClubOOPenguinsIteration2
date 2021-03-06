package View.StatusView;


import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import View.Viewport;

import java.awt.*;
import java.util.ArrayList;


public class StatusViewPort extends Viewport {

    private int currentView = 2;
    private ArrayList<String> list = new ArrayList<>();

    public StatusViewPort(Player player){
        add(new StatsView(player));
        add(new HotBarView(player.getEquipment()));
        add(new EquipmentView(player.getEquipment()));
        add(new InventoryView(player.getInventory()));
        add(new SkillsView(player.getRole()));
    }

    @Override
    public void draw(Graphics2D graphics2D){
        children.get(0).draw(graphics2D);
        children.get(1).draw(graphics2D);
        switch (currentView){
            case 1:
                children.get(2).draw(graphics2D);
                break;
            case 2:
                children.get(3).draw(graphics2D);
                break;
            case 3:
                children.get(4).draw(graphics2D);
                break;
        }
        for(int i = 0; i < list.size(); ++i){
            graphics2D.drawString(list.get(i), 10, 30 * i);
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

    public void drawObservation(ArrayList<String> list) {
        this.list = list;
    }
}
