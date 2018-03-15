package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Item.TakeableItem.BrawlingItem.SwordHands;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;

public class RadialIceBomb extends BaneItem {

    private double damageAmount = 0.5;
    private double radialDecreaseFactor = 0.25;

    private double speed = 0.75;
    private double lastMoved;

    private List<Location> locationsOn;
    private HashMap<Location,Direction> directionHeading = new HashMap<Location,Direction>();
    private boolean done = false;
    private boolean onFirstWave = true;

    @Override
    public List<Location> getLocationsOn() {
        return locationsOn;
    }

    @Override
    public void update() {
        if (Time.currentInSeconds() > lastMoved + speed) {
            if (!done) {
                if(onFirstWave)
                    firstMove();
                else
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

        locationsOn.add(locationOfEntity);
        damageAmount *= baneSkillLevel;
        radialDecreaseFactor *= baneSkillLevel;

        RadialIceBomb projectile;
        try {
            projectile = (RadialIceBomb) ((RadialIceBomb) this).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // TODO: add projectile to updateable list

    }

    private void move(){
        List<Location> nextWave = new ArrayList<Location>();
        for (Location location : locationsOn) {
            // if it hits end of the map
            if(location.getAdjacentAt(directionHeading.get(location)) != null){
                Location nextLocation = location.getAdjacentAt(directionHeading.get(location));
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation, directionHeading.get(location));
                }
            }
            if(location.getAdjacentAt(getClockwiseOf(directionHeading.get(location))) != null){
                Location nextLocation = location.getAdjacentAt(getClockwiseOf(directionHeading.get(location)));
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation, directionHeading.get(location));
                }
            }
            if(location.getAdjacentAt(getCounterClockwiseOf(directionHeading.get(location))) != null){
                Location nextLocation = location.getAdjacentAt(getCounterClockwiseOf(directionHeading.get(location)));
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation, directionHeading.get(location));
                }
            }
        }
        for (Location location : locationsOn){
            directionHeading.remove(location);
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
                directionHeading.remove(currentLocation);
            }
        }
        damageAmount -= radialDecreaseFactor;
        if(damageAmount <= 0)
            done = true;
    }

    private void firstMove(){
        List<Location> nextWave = new ArrayList<Location>();
        for (Location location : locationsOn) {
            // if it hits end of the map
            if(location.getAdjacentAt(N) != null){
                Location nextLocation = location.getAdjacentAt(N);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,N);
                }
            }
            if(location.getAdjacentAt(NE) != null){
                Location nextLocation = location.getAdjacentAt(NE);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,NE);
                }
            }
            if(location.getAdjacentAt(E) != null){
                Location nextLocation = location.getAdjacentAt(E);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,E);
                }
            }
            if(location.getAdjacentAt(SE) != null){
                Location nextLocation = location.getAdjacentAt(SE);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,SE);
                }
            }
            if(location.getAdjacentAt(S) != null){
                Location nextLocation = location.getAdjacentAt(S);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,S);
                }
            }
            if(location.getAdjacentAt(SW) != null){
                Location nextLocation = location.getAdjacentAt(SW);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,SW);
                }
            }
            if(location.getAdjacentAt(W) != null){
                Location nextLocation = location.getAdjacentAt(W);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,W);
                }
            }
            if(location.getAdjacentAt(NW) != null){
                Location nextLocation = location.getAdjacentAt(NW);
                if (!nextWave.contains(nextLocation)) {
                    nextWave.add(nextLocation);
                    directionHeading.put(nextLocation,NW);
                }
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
                directionHeading.remove(currentLocation);
            }
        }
        damageAmount -= radialDecreaseFactor;
        if(damageAmount <= 0)
            done = true;
        onFirstWave = false;
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