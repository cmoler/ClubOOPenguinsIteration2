package Model.Item.TakeableItem.Projectile;

import Model.Entity.Entity;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.UpdateList;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.List;

public class LinearProjectile implements Projectile {

    private double damageAmount;
    private double linearDecreaseFactor;

    private double speed;
    private double lastMoved;

    private Location locationOn;
    private Direction directionHeading;
    private boolean done = false;

    private String appearanceType;

    public LinearProjectile(double damageAmount, double linearDecreaseFactor,
                            double speed, Location locationOn, Direction directionHeading,
                            String appearanceType){
        this.damageAmount = damageAmount;
        this.linearDecreaseFactor = linearDecreaseFactor;
        this.speed = speed;
        this.locationOn = locationOn;
        this.directionHeading = directionHeading;
        this.appearanceType = appearanceType;
        UpdateList.getInstance().add(this);
    }

    @Override
    public List<Location> getLocationsOn() {
        List<Location> locations = new ArrayList<Location>();
        if(locationOn == null)
            return locations;
        locations.add(locationOn);
        return locations;
    }

    @Override
    public String getAppearanceType() {
        return appearanceType;
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
            if(damageAmount <= 0 || locationOn.hasObstacle())
                done = true;
        }
    }
}
