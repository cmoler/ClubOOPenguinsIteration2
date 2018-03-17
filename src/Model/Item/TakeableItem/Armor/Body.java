package Model.Item.TakeableItem.Armor;

import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Body extends WearableItem {

    public Body(){
        super();
        this.name = "chest";
    }
    @Override
    public void putOn(Player player) {

    }

    @Override
    public void takeOff(Player player) {

    }

    @Override
    public String getSlot() {
        return "body";
    }
}
