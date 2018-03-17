package Model.Item.TakeableItem.BrawlingItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

public abstract class BrawlingItem extends UseableItem {

    private double lastUse;

    public BrawlingItem(){
        super();
    }

    public boolean canEquip(Player entity) {
        // ok under OCP
        if(entity.getRole().getClass() == Smasher.class)
            return true;
        else
            return false;
    }

    public void use(Player entityUsingItem, Location locationOfEntity) {
        if(Time.currentInSeconds() > lastUse + getSecondsPerUse()) {

            Smasher role = (Smasher) entityUsingItem.getRole();
            double brawlSkillLevel = (double) role.getBrawl();

            Direction directionFacing = entityUsingItem.getDirectionFacing();
            Location locationOfTarget = locationOfEntity.getAdjacentAt(directionFacing);
            Map currentMap = World.getWorld().getCurrentMap();
            if(currentMap.entityAtLocation(locationOfTarget) != null){
                Entity entityAtTarget = currentMap.entityAtLocation(locationOfTarget);
                entityAtTarget.takeDamage( (int)(getDamageAmount()*brawlSkillLevel) );
            }

            lastUse = Time.currentInSeconds();
        }
    }

    protected abstract double getSecondsPerUse();

    protected abstract double getDamageAmount();
}
