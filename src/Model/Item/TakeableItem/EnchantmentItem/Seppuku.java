package Model.Item.TakeableItem.EnchantmentItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;

public class Seppuku extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use() {

    }
}
