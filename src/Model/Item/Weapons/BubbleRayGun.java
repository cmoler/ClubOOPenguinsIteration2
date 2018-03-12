package Model.Item.Weapons;

import Model.Entity.Entity;
import Model.Item.TakeableItem;

public class BubbleRayGun extends TakeableItem {
    //ranged

    @Override
    public boolean canEquip(Entity entity) {
        //check that entity has ranged skill
        return false;
    }

    @Override
    public void use() {

    }
}
