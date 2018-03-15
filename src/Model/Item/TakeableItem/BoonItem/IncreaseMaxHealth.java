package Model.Item.TakeableItem.BoonItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

import java.util.Random;

public class IncreaseMaxHealth extends BoonItem {

    private int maxHealthIncrement = 40;

    @Override
    protected void apply(Entity entityUsingItem) {
        entityUsingItem.modifyMaxHealth(maxHealthIncrement);
    }
}