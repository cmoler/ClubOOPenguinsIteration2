package Model.Item.TakeableItem.OneHandedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.concurrent.TimeUnit;

public class BlueLightsaber extends TakeableItem{

    private int damageAmount = 20;
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

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            // TODO: if Entity on locationOfTarget: decrement Entity's health based on skill level
            Smasher role = (Smasher) entityUsingItem.getRole();
            int oneHandedWeaponSkillLevel = role.getOneHandedWeapon();

            lastUse = Time.currentInSeconds();
        }
    }
}
