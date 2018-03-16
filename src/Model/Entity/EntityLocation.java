package Model.Entity;

import Model.Map.Location;

public class EntityLocation {

    private Entity entity;
    private Location location;

    public EntityLocation(Entity entity, Location location){
        this.entity = entity;
        this.location = location;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}
