package Model.Item.TakeableItem.TwoHandedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

public class JeweledCutlass extends TwoHandedWeaponItem{

    private double damageAmount = 2.0; // gets multiplied by skill level
    private double secondsPerUse = 2.0;

    @Override
    protected double getSecondsPerUse() {
        return secondsPerUse;
    }

    @Override
    protected double getDamageAmount() {
        return damageAmount;
    }

    @Override
    public void use(Entity entityUsingItem, Location locationOfEntity) {

    }
}
