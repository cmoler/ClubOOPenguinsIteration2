package Model.Item.TakeableItem;

import Model.Entity.Entity;

public abstract class WearableItem extends TakeableItem {

    public abstract void putOn(Entity entity);

    public abstract void takeOff(Entity entity);
    public abstract String getSlot();

    public boolean canWear() { return true; }
}