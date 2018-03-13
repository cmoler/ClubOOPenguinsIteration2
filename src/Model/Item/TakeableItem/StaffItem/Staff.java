package Model.Item.TakeableItem.StaffItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;

public class Staff extends TakeableItem{

    private int manaIncrement = 30;

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        Direction directionFacing = entityUsingItem.getDirectionFacing();
        Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
        // TODO: if Entity on locationOfTarget: give mana to entityUsingItem

    }
}
