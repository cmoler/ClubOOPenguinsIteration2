package Controller.Controllers;

import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Player;
import Model.Map.Direction;

public class PlayerController implements Controller {

    Player player;
    Equipment equipment;

    public PlayerController(GameLoader gameLoader){
        player = gameLoader.getPlayer();
        equipment = gameLoader.getEquipment();
    }

    @Override
    public void setActive() {

    }

    public void move(Direction direction){
        player.move(direction);
    }

    public void useItem(int index){
        equipment.useItem(index);
    }
}
