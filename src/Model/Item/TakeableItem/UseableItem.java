package Model.Item.TakeableItem;

import Model.Entity.Entity;
import Model.Map.Location;

public abstract class UseableItem extends TakeableItem {

    public abstract void use(Entity entityUsingItem, Location locationOfEntity);
}
