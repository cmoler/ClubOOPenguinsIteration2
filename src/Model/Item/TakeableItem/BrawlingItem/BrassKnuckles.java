package Model.Item.TakeableItem.BrawlingItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;

public class BrassKnuckles extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Smasher.class)
            return true;
        else
            return false;
    }

    public void use() {

    }
}
