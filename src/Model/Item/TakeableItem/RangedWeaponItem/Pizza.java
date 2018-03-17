package Model.Item.TakeableItem.RangedWeaponItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.BaneItem.RadialIceBomb;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.Projectile.RadialProjectile;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;
import static Model.Map.Direction.N;
import static Model.Map.Direction.NE;

public class Pizza extends RangedWeaponItem {

    public Pizza(){
        super();
        this.name = "pizza";
    }

    private double damageAmount = 0.5;
    private double radialDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int rangedWeaponSkillLevel) {

        Projectile projectile = new RadialProjectile(damageAmount*rangedWeaponSkillLevel,
                radialDecreaseFactor*rangedWeaponSkillLevel, speed,
                locationOfEntity, "Pizza");

        super.addProjectile(projectile);
    }

    @Override
    public String save(Saver saver) {
        return saver.savePizza(this);
    }

}
