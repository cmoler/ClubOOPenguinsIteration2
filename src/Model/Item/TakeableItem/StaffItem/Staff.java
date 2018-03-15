package Model.Item.TakeableItem.StaffItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class Staff extends TakeableItem{

    private int damage = 15;

    public boolean canEquip(Player entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Player entityUsingItem, Location locationOfEntity) {
        Direction directionFacing = entityUsingItem.getDirectionFacing();
        Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
        Map currentMap = World.getWorld().getCurrentMap();
        if(currentMap.entityAtLocation(locationOfTarget) != null){
            Entity entityAtTarget = currentMap.entityAtLocation(locationOfTarget);
            entityAtTarget.takeDamage(damage);
        }
    }
}
