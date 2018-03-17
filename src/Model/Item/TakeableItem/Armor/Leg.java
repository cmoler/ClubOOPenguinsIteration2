package Model.Item.TakeableItem.Armor;

import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Leg extends WearableItem {

    public Leg(){
        super();
        this.name = "legs";
    }
    @Override
    public void putOn(Player player) {

    }

    @Override
    public void takeOff(Player player) {

    }

    @Override
    public String getSlot() {
        return "legs";
    }
}
