package Model.Item.TakeableItem.RangedWeaponItem;

import Model.Entity.Entity;
import Model.Entity.Role.Sneak;
import Model.Item.TakeableItem.BaneItem.AngularIceAttack;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;

public class SnowShuriken extends RangedWeaponItem {

    private double damageAmount = 0.75;
    private double angularDecreaseFactor = 0.25;

    private double speed = 0.75;
    private double lastMoved;

    private List<Location> locationsOn;
    private Direction directionHeading;
    private boolean done = false;

    @Override
    public List<Location> getLocationsOn() {
        return locationsOn;
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
    protected void apply(Location locationOfEntity, Direction directionFacing, int rangedWeaponSkillLevel) {

        locationsOn.add(locationOfEntity);
        damageAmount *= rangedWeaponSkillLevel;
        angularDecreaseFactor *= rangedWeaponSkillLevel;
        directionHeading = directionFacing;

        SnowShuriken projectile;
        try {
            projectile = (SnowShuriken) ((SnowShuriken) this).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // TODO: add projectile to updateable list

    }

    private void move(){
        List<Location> nextWave = new ArrayList<Location>();
        for (Location location : locationsOn) {
            // if it hits end of the map
            if(location.getAdjacentAt(directionHeading) != null){
                Location nextLocation = location.getAdjacentAt(directionHeading);
                if (!nextWave.contains(nextLocation))
                    nextWave.add(nextLocation);
            }
            if(location.getAdjacentAt(getClockwiseOf(directionHeading)) != null){
                Location nextLocation = location.getAdjacentAt(getClockwiseOf(directionHeading));
                if (!nextWave.contains(nextLocation))
                    nextWave.add(nextLocation);
            }
            if(location.getAdjacentAt(getCounterClockwiseOf(directionHeading)) != null){
                Location nextLocation = location.getAdjacentAt(getCounterClockwiseOf(directionHeading));
                if (!nextWave.contains(nextLocation))
                    nextWave.add(nextLocation);
            }
        }
        locationsOn = nextWave;
        if (locationsOn.isEmpty()){
            done = true;
            return;
        }
        Map currentMap = World.getWorld().getCurrentMap();
        for (Iterator<Location> iterator = locationsOn.iterator(); iterator.hasNext();) {
            Location currentLocation = iterator.next();
            if(currentMap.entityAtLocation(currentLocation) != null){
                Entity entityAtTarget = currentMap.entityAtLocation(currentLocation);
                entityAtTarget.takeDamage( (int)(damageAmount) );
                iterator.remove();
            }
        }
        damageAmount -= angularDecreaseFactor;
        if(damageAmount <= 0)
            done = true;
    }

    private Direction getClockwiseOf(Direction d){
        switch (d){
            case N:
                return NE;
            case NE:
                return E;
            case E:
                return SE;
            case SE:
                return S;
            case S:
                return SW;
            case SW:
                return W;
            case W:
                return NW;
            case NW:
                return N;
        }
        return null;
    }

    private Direction getCounterClockwiseOf(Direction d){
        switch (d){
            case N:
                return NW;
            case NW:
                return W;
            case W:
                return SW;
            case SW:
                return S;
            case S:
                return SE;
            case SE:
                return E;
            case E:
                return NE;
            case NE:
                return N;
        }
        return null;
    }
}
