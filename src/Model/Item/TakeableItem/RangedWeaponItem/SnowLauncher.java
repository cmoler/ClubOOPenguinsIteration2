package Model.Item.TakeableItem.RangedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.TakeableItem;

public class SnowLauncher extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Sneak.class)
            return true;
        else
            return false;
    }

    public void use() {

    }
}
