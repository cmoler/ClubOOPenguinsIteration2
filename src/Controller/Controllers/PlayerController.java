package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Player;
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
}
