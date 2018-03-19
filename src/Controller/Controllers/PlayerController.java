package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Player;
import Model.Map.Direction;
import View.AreaView.AreaViewPort;

public class PlayerController implements Controller {

    Player player;
    Equipment equipment;
    private AreaViewPort areaViewPort;

    public PlayerController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getAreaViewport();
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
}
