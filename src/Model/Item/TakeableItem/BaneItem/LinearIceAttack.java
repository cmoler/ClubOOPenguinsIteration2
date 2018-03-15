package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.List;

public class LinearIceAttack extends BaneItem {

    private int manaNeeded = 10;

    private double damageAmount = 1.0;
    private double linearDecreaseFactor = 0.25;

    private double speed = 0.75;
    private double lastMoved;

    private Location locationOn;
    private Direction directionHeading;
    private boolean done = false;

    @Override
    public List<Location> getLocationsOn() {
        List<Location> locations = new ArrayList<Location>();
        if(locationOn == null)
            return locations;
        locations.add(locationOn);
        return locations;
    }

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    public void update() {
        if (Time.currentInSeconds() > lastMoved + speed) {
            if (!done) {
                move();
                lastMoved = Time.currentInSeconds();
            }
        }
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    protected void apply(Location locationOfEntity, Direction directionFacing, int baneSkillLevel) {

        locationOn = locationOfEntity;
        damageAmount *= baneSkillLevel;
        linearDecreaseFactor *= baneSkillLevel;
        directionHeading = directionFacing;

        LinearIceAttack projectile;
        try {
            projectile = (LinearIceAttack) ((LinearIceAttack) this).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // TODO: add projectile to updateable list

    }

    private void move(){
        Map currentMap = World.getWorld().getCurrentMap();
        // if it hits end of the map
        if (locationOn.getAdjacentAt(directionHeading) == null) {
            locationOn = null;
            done = true;
            return;
        }
        locationOn = locationOn.getAdjacentAt(directionHeading);
        if(currentMap.entityAtLocation(locationOn) != null){
            Entity entityAtTarget = currentMap.entityAtLocation(locationOn);
            entityAtTarget.takeDamage( (int)(damageAmount) );
            locationOn = null;
            done = true;
        }
        else{
            damageAmount -= linearDecreaseFactor;
            if(damageAmount <= 0)
                done = true;
        }
    }
}
