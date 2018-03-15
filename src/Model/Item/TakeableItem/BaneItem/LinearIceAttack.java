package Model.Item.TakeableItem.BaneItem;

import Model.Item.TakeableItem.Projectile.LinearProjectile;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Map.Direction;
import Model.Map.Location;

public class LinearIceAttack extends BaneItem {

    private double damageAmount = 1.0;
    private double linearDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel) {
        Projectile projectile = new LinearProjectile(damageAmount*baneSkillLevel,
                linearDecreaseFactor*baneSkillLevel,
                speed, locationOfEntity, directionFacing,"Ice");

        super.addProjectile(projectile);
    }
}
