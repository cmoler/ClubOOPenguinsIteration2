package Model.Item.Weapons;

import Model.Entity.Entity;
import Model.Item.TakeableItem;

public class SwordOfVirtue extends TakeableItem {
    //two-handed

    @Override
    public boolean canEquip(Entity entity) {
        //check that entity has two-handed skill
        return false;
    }

    @Override
    public void use() {

    }
}
