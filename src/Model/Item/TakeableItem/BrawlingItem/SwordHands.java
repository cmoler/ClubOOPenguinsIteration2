package Model.Item.TakeableItem.BrawlingItem;

import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

public class SwordHands extends BrawlingItem {

    private double damageAmount = 0.75; // gets multiplied by skill level
    private double secondsPerUse = 0.75;

    @Override
    protected double getSecondsPerUse() {
        return secondsPerUse;
    }

    @Override
    protected double getDamageAmount() {
        return damageAmount;
    }

}
