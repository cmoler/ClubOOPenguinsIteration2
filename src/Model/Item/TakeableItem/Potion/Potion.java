package Model.Item.TakeableItem.Potion;

import Model.Entity.Player;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;

public abstract class Potion extends UseableItem {


    public Potion(){
        value = 10;
    }

    public boolean canEquip(Player entity) {
        // ok under OCP
        return true;
    }

    @Override
    public void use(Player entityUsingItem, Location locationOfEntity) {
        apply(entityUsingItem);
        entityUsingItem.getEquipment().unEquip(this);
    }

    protected abstract void apply(Player entityUsingItem);
}
