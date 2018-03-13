package Model.Item.TakeableItem.EnchantmentItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;

import java.util.Random;

public class Seppuku extends TakeableItem{

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        Summoner role = (Summoner) entityUsingItem.getRole();
        int enchantmentSkillLevel = role.getEnchantment();
        Random rand = new Random();
        Direction directionFacing = entityUsingItem.getDirectionFacing();
        Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
        // TODO: if NPC on locationOfTarget:
        if(rand.nextInt(100)+1 <= enchantmentSkillLevel) {
            // TODO: make NPC die
        }
        else{
            // TODO: piss off NPC
        }
        entityUsingItem.getInventory().removeItem(this);
    }
}
