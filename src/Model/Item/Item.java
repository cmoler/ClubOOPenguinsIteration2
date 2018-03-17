package Model.Item;

import Model.Entity.Player;

public abstract class Item {

    public abstract void touch(Player entity);

    public abstract boolean shouldBeRemoved();
}
