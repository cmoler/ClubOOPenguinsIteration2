package Model.Item;

import Model.Entity.Player;

public abstract class Item {

    protected String name;

    public String getName(){
        return name;
    }

    public abstract void touch(Player entity);

    public abstract boolean shouldBeRemoved();
}
