package Model.Item.TakeableItem.TwoHandedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public abstract class TwoHandedWeaponItem extends UseableItem {

    private double lastUse;

    public boolean canEquip(Player entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Smasher.class)
            return true;
        else
            return false;
    }

    public void use(Player entityUsingItem, Location locationOfEntity) {
        if(Time.currentInSeconds() > lastUse + getSecondsPerUse()) {

            Smasher role = (Smasher) entityUsingItem.getRole();
            double twoHandedWeaponSkillLevel = (double) role.getTwoHandedWeapon();

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            Map currentMap = World.getWorld().getCurrentMap();
            if(currentMap.entityAtLocation(locationOfTarget) != null){
                Entity entityAtTarget = currentMap.entityAtLocation(locationOfTarget);
                entityAtTarget.takeDamage( (int)(getDamageAmount()*twoHandedWeaponSkillLevel) );
            }

            lastUse = Time.currentInSeconds();
        }
    }

    protected abstract double getSecondsPerUse();

    protected abstract double getDamageAmount();
}
