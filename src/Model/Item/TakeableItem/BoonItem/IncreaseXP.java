package Model.Item.TakeableItem.BoonItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

import java.util.Random;

public class IncreaseXP extends BoonItem {

    private int manaNeeded = 100;
    private int XPIncrement = 40;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Entity entityUsingItem) {
        entityUsingItem.gainExperience(XPIncrement);
    }
}
