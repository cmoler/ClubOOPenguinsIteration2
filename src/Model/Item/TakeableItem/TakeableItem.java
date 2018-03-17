package Model.Item.TakeableItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.Item;
import Model.Map.Location;

public abstract class TakeableItem extends Item {

    @Override
    public final void touch(Player entity) {
        entity.getInventory().addItem(this);
    }

    public boolean canEquip(Player entity) {
        return false;
    }

    public boolean canWear() { return false; }

}
