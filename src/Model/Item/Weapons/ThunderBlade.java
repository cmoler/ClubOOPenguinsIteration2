package Model.Item.Weapons;

import Model.Entity.Entity;
import Model.Item.TakeableItem;

public class ThunderBlade extends TakeableItem {
    //one-handed

    @Override
    public boolean canEquip(Entity entity) {
        //check that entity has one-handed skill
        return false;
    }

    @Override
    public void use() {

    }
}
