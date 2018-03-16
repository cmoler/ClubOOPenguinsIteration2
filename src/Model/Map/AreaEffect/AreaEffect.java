package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Map.Location;

public abstract class AreaEffect {
    protected Location location;

    public void setLocation(Location location){
        this.location = location;
    }

    public abstract void activate(Entity entity);

    protected abstract void affect(Entity entity);

    public abstract AreaEffectType getAreaEffectType();
}
