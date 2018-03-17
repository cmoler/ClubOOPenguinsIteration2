package Model.Item.TakeableItem.RangedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.BaneItem.AngularIceAttack;
import Model.Item.TakeableItem.Projectile.AngularProjectile;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;

public class SnowShuriken extends RangedWeaponItem {

    public SnowShuriken(){
        super();
        this.name = "snowShuriken";
    }

    private double damageAmount = 0.75;
    private double angularDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int rangedWeaponSkillLevel) {

        Projectile projectile = new AngularProjectile(damageAmount*rangedWeaponSkillLevel,
                angularDecreaseFactor*rangedWeaponSkillLevel, speed, locationOfEntity,
                directionFacing, "Snow");

        super.addProjectile(projectile);
    }

}
