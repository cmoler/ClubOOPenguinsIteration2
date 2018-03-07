package Model.Item;

import Model.Entity.Entity;

public abstract class Item {

    public abstract void touch(Entity entity);

    public abstract boolean shouldBeRemoved();

    public abstract ItemType getItemType();
}
