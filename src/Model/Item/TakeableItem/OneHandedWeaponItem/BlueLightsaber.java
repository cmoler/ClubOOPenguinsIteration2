package Model.Item.TakeableItem.OneHandedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

public class BlueLightsaber extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Smasher.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        
    }
}
