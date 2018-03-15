package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Updateable;
import Model.Utilites.Time;

import java.util.List;

public abstract class BaneItem extends ProjectileCapableItem {

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
            if(entityUsingItem.getMana() > getManaNeeded()) {

                Summoner role = (Summoner) entityUsingItem.getRole();
                int baneSkillLevel = role.getBane();

                Direction directionFacing = entityUsingItem.getDirectionFacing();
                apply(locationOfEntity, directionFacing, baneSkillLevel);

                lastUse = Time.currentInSeconds();
                entityUsingItem.addMana(-getManaNeeded());
            }
        }
    }

    protected abstract int getManaNeeded();
    protected abstract void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel);
}
