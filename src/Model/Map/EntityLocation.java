package Model.Map;

import Model.Entity.Entity;

import java.util.HashMap;
import java.util.Iterator;


public class EntityLocation {

    private HashMap<Location, Entity> entityLocations;

    public EntityLocation() {
        entityLocations = new HashMap<>();
    }

    public Entity getEntityAtLocation(Location location) {
        return entityLocations.get(location);
    }

    public void setEntityLocation(Location location, Entity entity) {
        entityLocations.put(location, entity);
    }

    public void removeEntityLocation(Location location) {
        entityLocations.remove(entityLocations.get(location));
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
                        interactEntity();
                    }
                }
                currentEntity.setIntentToMove(false);
            }
        }
    }

    public void moveEntity(Location currentLocation, Location nextLocation, Entity currentEntity) {
        if (nextLocation.moveAllowed(currentEntity)) {
            entityLocations.remove(currentLocation, currentEntity);
            entityLocations.put(nextLocation, currentEntity);
            currentEntity.setLocation(nextLocation);
            nextLocation.activateAreaEffect(currentEntity);
            currentEntity.touchItems();
        }
    }

    public void interactEntity() {
        //TODO Do Something
    }


}
