package Model.Item.TakeableItem;

import Model.Entity.Entity;
import Model.Item.Item;
import Model.Map.Location;

public class TakeableItem extends Item {

    @Override
    public final void touch(Entity entity) {
        entity.getInventory().addItem(this);
    }

    public boolean canEquip(Entity entity) {
        return false;
    }

    public boolean canWear() { return false; }

}
