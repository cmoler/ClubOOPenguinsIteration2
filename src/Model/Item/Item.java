package Model.Item;

import Model.Entity.Player;
import Model.SaveableItem;

public abstract class Item implements SaveableItem{

    public abstract void touch(Player entity);
}
