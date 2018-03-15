package Model.Item.TakeableItem.Armour;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.WearableItem;

public class Helmet extends WearableItem{

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
