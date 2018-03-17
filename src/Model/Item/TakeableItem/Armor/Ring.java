package Model.Item.TakeableItem.Armor;

import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Ring extends WearableItem {

    public Ring(){
        super();
        this.name = "ring";
    }
    @Override
    public void putOn(Player player) {

    }

    @Override
    public void takeOff(Player player) {

    }

    @Override
    public String getSlot() {
        return "ring";
    }
}
