package Model.Item.TakeableItem.BaneItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Item.TakeableItem.BrawlingItem.SwordHands;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.Projectile.RadialProjectile;
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

public class RadialIceBomb extends BaneItem {

    public RadialIceBomb(){
        super();
        this.name = "radialIceAttack";
    }

    private int manaNeeded = 20;

    private double damageAmount = 0.5;
    private double radialDecreaseFactor = 0.2;

    private double speed = 0.75;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel) {

        Projectile projectile = new RadialProjectile(damageAmount*baneSkillLevel,
                radialDecreaseFactor*baneSkillLevel, speed,
                locationOfEntity, "Ice");

        super.addProjectile(projectile);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveRadialIceBomb(this);
    }
}
