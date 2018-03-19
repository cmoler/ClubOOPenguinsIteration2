package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Player;
import Model.Entity.Role.RoleType;
import Model.Entity.Role.Sneak;
import Model.Map.Direction;
import View.AreaView.AreaViewPort;
import View.Viewport;

public class PlayerController implements Controller {

    Player player;
    Equipment equipment;
    private Viewport areaViewPort;

    public PlayerController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getViewport();
        player = gameBuilder.getPlayer();
        equipment = gameBuilder.getEquipment();
    }

    @Override
    public void setActive() {
        areaViewPort.setVisible(true);
        areaViewPort.requestFocus();
    }

    public void move(Direction direction){
        player.move(direction);
    }

    public void useItem(int index){
        equipment.useItem(index);
    }

    public void pickPocket() {
        if(player.getRole().getRoleType() == RoleType.Sneak){
            ((Sneak)player.getRole()).pickPocket();
        }
    }

    public void creep() {
        if(player.getRole().getRoleType() == RoleType.Sneak){
            ((Sneak)player.getRole()).creep();
        }
    }

    public void removeTrap() {
        if(player.getRole().getRoleType() == RoleType.Sneak){
            ((Sneak)player.getRole()).removeTrap();
        }
    }

    public void bindWounds() {
        if(player.getRole().getRoleType() == RoleType.Sneak){
            player.getRole().bindWounds();
        }
    }
}
