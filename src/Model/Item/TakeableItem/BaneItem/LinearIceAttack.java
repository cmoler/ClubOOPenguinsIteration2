package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Utilites.Time;

public class LinearIceAttack extends TakeableItem {

    private double damageAmount = 1.0;
    private double angularDecreaseFactor = 0.25;
    private double secondsPerUse = 1.25;
    private double lastUse;

    public boolean canEquip(Entity entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Summoner.class)
            return true;
        else
            return false;
    }

    public void use(Entity entityUsingItem, Location locationOfEntity) {
        if(Time.currentInSeconds() > lastUse + secondsPerUse) {

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            while(damageAmount > 0){
                // TODO: if NPC is on locationOfTarget, affect damage on NPC
                // break;
                // else
                damageAmount -= angularDecreaseFactor;
            }
            Summoner role = (Summoner) entityUsingItem.getRole();
            int baneSkillLevel = role.getBane();

            lastUse = Time.currentInSeconds();
        }
    }
}
