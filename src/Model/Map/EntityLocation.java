package Model.Map;

import Model.Entity.Entity;
import Model.Updateable;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;


public class EntityLocation{

    private ConcurrentHashMap<Location, Entity> entityLocations;

    public EntityLocation() {
        entityLocations = new ConcurrentHashMap<>();
    }

    public Entity getEntityAtLocation(Location location) {
        return entityLocations.get(location);
    }

    public void setEntityLocation(Location location, Entity entity) {
        entityLocations.put(location, entity);
        entity.setLocation(location);
    }

    public void removeEntityLocation(Location location) {
        entityLocations.remove(location);
    }

    public void updateEntityLocations() {
        Iterator<Location> occupiedLocations = entityLocations.keySet().iterator();

        while (occupiedLocations.hasNext()) {
            Location currentLocation = occupiedLocations.next();
            Entity currentEntity = entityLocations.get(currentLocation);

            //Check if Entity has intent to move
            if (currentEntity.getIntentToMove()) {
                Location nextLocation = currentLocation.getAdjacentAt(currentEntity.getDirectionFacing());
                if (nextLocation != null){
                    //Move entity if there is no other entity on nextLocation
                    if (!entityLocations.containsKey(nextLocation)) {
                        moveEntity(currentLocation, nextLocation, currentEntity);
                    }
                    //If there is an entity on nextLocation, interact
                    else {
                       currentEntity.interactEntity(entityLocations.get(nextLocation));
                    }
                }
                currentEntity.setIntentToMove(false);
            }
        }
    }

    public void moveEntity(Location currentLocation, Location nextLocation, Entity currentEntity) {
        if (nextLocation.moveAllowed(currentEntity)) {
            entityLocations.remove(currentLocation, currentEntity);
            setEntityLocation(nextLocation, currentEntity);
            currentEntity.interactLocation();
        }
    }

    public ArrayList<Pair> getAssociations(){
        ArrayList<Pair> p = new ArrayList<>();
        Iterator<Location> e = entityLocations.keySet().iterator();
        while(e.hasNext()){
            Location location = e.next();
            p.add(new Pair(location, entityLocations.get(location)));
        }
        return p;
    }
}
