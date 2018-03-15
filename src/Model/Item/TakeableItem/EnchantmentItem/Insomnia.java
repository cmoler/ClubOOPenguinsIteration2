package Model.Item.TakeableItem.EnchantmentItem;

import Model.Entity.Entity;
import Model.Entity.NPC.NPC;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.Random;

public class Insomnia extends EnchantmentItem {

    private int manaNeeded = 10;

    protected void apply(Entity entityAtTarget){
        ((NPC) entityAtTarget).fallAsleep();
    }

    protected int getManaNeeded(){
        return manaNeeded;
    }

    @Override
    public void use(Entity entityUsingItem, Location locationOfEntity) {

    }
}
