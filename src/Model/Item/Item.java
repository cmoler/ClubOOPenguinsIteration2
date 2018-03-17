package Model.Item;

import Model.Entity.Player;

public abstract class Item {

    protected String name;
    protected int value;

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public abstract void touch(Player entity);

    public abstract boolean shouldBeRemoved();
}
