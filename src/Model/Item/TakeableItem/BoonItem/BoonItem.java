package Model.Item.TakeableItem.BoonItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

import java.util.Random;

public abstract class BoonItem extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        Summoner role = (Summoner) entityUsingItem.getRole();
        int boonSkillLevel = role.getBoon();
        Random rand = new Random();
        if(rand.nextInt(100)+1 <= boonSkillLevel) {
            apply(entityUsingItem); // template method
        }

        entityUsingItem.getInventory().removeItem(this);
    }

    protected abstract void apply(Entity entityUsingItem);
}
