package Model.Item.TakeableItem;

import Model.Entity.Entity;
import Model.Entity.Player;

public abstract class WearableItem extends TakeableItem {

    public abstract void putOn(Player player);

    public abstract void takeOff(Player player);

    public abstract String getSlot();

    public boolean canWear() { return true; }
}