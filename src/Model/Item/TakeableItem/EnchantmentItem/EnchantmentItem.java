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

public abstract class EnchantmentItem extends TakeableItem {

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {

        if(entityUsingItem.getMana() > getManaNeeded()) {

            Summoner role = (Summoner) entityUsingItem.getRole();
            int enchantmentSkillLevel = role.getEnchantment();

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            Map currentMap = World.getWorld().getCurrentMap();
            if (currentMap.entityAtLocation(locationOfTarget) != null) {
                Entity entityAtTarget = currentMap.entityAtLocation(locationOfTarget);
                Random rand = new Random();
                if (rand.nextInt(100) + 1 <= enchantmentSkillLevel) {
                    apply(entityAtTarget); // template method
                } else {
                    ((NPC) entityAtTarget).pissOff();
                }
            }

            entityUsingItem.addMana(-getManaNeeded());
        }

    }

    protected abstract void apply(Entity entityAtTarget);

    protected abstract int getManaNeeded();
}