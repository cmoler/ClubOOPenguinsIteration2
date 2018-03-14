package Model.Item.TakeableItem.OneHandedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

public class Mjolnir extends TakeableItem{

    private double damageAmount = 1.25; // gets multiplied by skill level
    private double secondsPerUse = 1.25;
    private double lastUse;

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Smasher.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        if(Time.currentInSeconds() > lastUse + secondsPerUse) {

            Smasher role = (Smasher) entityUsingItem.getRole();
            double oneHandedWeaponSkillLevel = (double) role.getOneHandedWeapon();

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            Map currentMap = World.getWorld().getCurrentMap();
            if(currentMap.entityAtLocation(locationOfTarget) != null){
                Entity entityAtTarget = currentMap.entityAtLocation(locationOfTarget);
                entityAtTarget.takeDamage( (int)(damageAmount*oneHandedWeaponSkillLevel) );
            }

            lastUse = Time.currentInSeconds();
        }
    }
}
