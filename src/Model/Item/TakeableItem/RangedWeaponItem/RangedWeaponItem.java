package Model.Item.TakeableItem.RangedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Updateable;
import Model.Utilites.Time;

import java.util.List;

public abstract class RangedWeaponItem extends ProjectileCapableItem {

    private double secondsPerUse = 1.25;
    private double lastUse;

    public RangedWeaponItem(){
        super();
    }

    public boolean canEquip(Player entity) {
        // ok under OCP
        if (entity.getRole().getClass() == Sneak.class)
            return true;
        else
            return false;
    }

    public void use(Player entityUsingItem, Location locationOfEntity) {
        if (Time.currentInSeconds() > lastUse + secondsPerUse) {

            Sneak role = (Sneak) entityUsingItem.getRole();
            int rangedWeaponSkillLevel = role.getRangedWeapon();

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            apply(locationOfEntity, directionFacing, rangedWeaponSkillLevel);

            lastUse = Time.currentInSeconds();
        }
    }

    protected abstract void apply(Location locationOfEntity, Direction directionFacing, int rangedWeaponSkillLevel);
}
