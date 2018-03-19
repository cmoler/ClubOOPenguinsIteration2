package Model.Item.TakeableItem.BaneItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.Projectile.AngularProjectile;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Updateable;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;

public class AngularIceAttack extends BaneItem {

    public AngularIceAttack(){
        super();
        this.name = "angularIceAttack";
    }

    private int manaNeeded = 5;
    private double damageAmount = 0.75;
    private double angularDecreaseFactor = 0.25;

    private double speed = 0.75;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel) {

        Projectile projectile = new AngularProjectile(damageAmount*baneSkillLevel,
                angularDecreaseFactor*baneSkillLevel, speed, locationOfEntity,
                directionFacing, "Angular Ice");

        super.addProjectile(projectile);

    }

    @Override
    public String save(Saver saver) {
        return saver.saveAngulariceAttack(this);
    }

}
