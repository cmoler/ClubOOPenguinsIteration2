package Model.Item.TakeableItem.RangedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.BaneItem.LinearIceAttack;
import Model.Item.TakeableItem.Projectile.LinearProjectile;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.List;

public class SnowLauncher extends RangedWeaponItem {

    private double damageAmount = 1.0;
    private double linearDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int rangedWeaponSkillLevel) {

        Projectile projectile = new LinearProjectile(damageAmount*rangedWeaponSkillLevel,
                linearDecreaseFactor*rangedWeaponSkillLevel, speed, locationOfEntity,
                directionFacing, "Snow");

        super.addProjectile(projectile);
    }

}
