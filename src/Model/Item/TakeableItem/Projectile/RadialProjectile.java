package Model.Item.TakeableItem.Projectile;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Item.TakeableItem.BaneItem.RadialIceBomb;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.UpdateList;
import Model.Utilites.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static Model.Map.Direction.*;

public class RadialProjectile implements Projectile {

    private double damageAmount;
    private double radialDecreaseFactor;

    private double speed;
    private double lastMoved;

    private List<Location> locationsOn;
    private HashMap<Location,Direction> directionHeading = new HashMap<Location,Direction>();
    private boolean done = false;
    private boolean onFirstWave = true;

    private String appearanceType;

    public RadialProjectile(double damageAmount, double radialDecreaseFactor,
                            double speed, Location locationOn,
                            String appearanceType){
        this.damageAmount = damageAmount;
        this.radialDecreaseFactor = radialDecreaseFactor;
        this.speed = speed;
        locationsOn = new ArrayList<Location>();
        locationsOn.add(locationOn);
        this.appearanceType = appearanceType;
        UpdateList.getInstance().add(this);
    }

    @Override
    public List<Location> getLocationsOn() {
        return locationsOn;
    }

    @Override
    public String getAppearanceType() {
        return appearanceType;
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
            if(currentLocation.hasObstacle())
                iterator.remove();
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
