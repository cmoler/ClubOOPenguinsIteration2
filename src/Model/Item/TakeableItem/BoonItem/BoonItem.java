package Model.Item.TakeableItem.BoonItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;

import java.util.Random;

public abstract class BoonItem extends UseableItem {

    public boolean canEquip(Player entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Player entityUsingItem, Location locationOfEntity) {
        if (entityUsingItem.getMana() > getManaNeeded()) {

            Summoner role = (Summoner) entityUsingItem.getRole();
            int boonSkillLevel = role.getBoon();
            Random rand = new Random();
            if (rand.nextInt(100) + 1 <= boonSkillLevel) {
                apply(entityUsingItem); // template method
            }
        }
    }


    protected abstract int getManaNeeded();

    protected abstract void apply(Entity entityUsingItem);
}
