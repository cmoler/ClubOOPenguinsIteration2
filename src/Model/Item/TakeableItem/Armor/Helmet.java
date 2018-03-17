package Model.Item.TakeableItem.Armor;

import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Helmet extends WearableItem{

    public Helmet(){
        super();
        this.name = "helmet";
    }

    private double defenseIncrease = 50;

    @Override
    public void putOn(Player player) {
        player.modifyDefense(defenseIncrease);
    }

    @Override
    public void takeOff(Player player) {
        player.modifyDefense(-defenseIncrease);
    }

    @Override
    public String getSlot() {
        return "head";
    }
}
