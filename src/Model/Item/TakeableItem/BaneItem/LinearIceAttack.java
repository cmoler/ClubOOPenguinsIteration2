package Model.Item.TakeableItem.BaneItem;

import Model.Item.TakeableItem.Projectile.LinearProjectile;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Map.Direction;
import Model.Map.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinearIceAttack extends BaneItem {

    private int manaNeeded = 10;

    private double damageAmount = 1.0;
    private double linearDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel) {
        Projectile projectile = new LinearProjectile(damageAmount*baneSkillLevel,
                linearDecreaseFactor*baneSkillLevel,
                speed, locationOfEntity, directionFacing,"Ice");

        super.addProjectile(projectile);
    }
}
