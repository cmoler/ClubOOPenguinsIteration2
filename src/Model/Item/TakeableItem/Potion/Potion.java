package Model.Item.TakeableItem.Potion;

import Model.Entity.Player;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;

public abstract class Potion extends UseableItem {


    public Potion(){
        value = 10;
    }

    @Override
    public void use(Player entityUsingItem, Location locationOfEntity) {
        apply(entityUsingItem);
        entityUsingItem.getEquipment().unEquip(this);
    }

    protected abstract void apply(Player entityUsingItem);
}
