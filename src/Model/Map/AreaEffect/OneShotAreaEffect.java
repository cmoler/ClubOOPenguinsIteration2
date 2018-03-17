package Model.Map.AreaEffect;

import Model.Entity.Entity;

public abstract class OneShotAreaEffect extends AreaEffect {

    boolean active = true;

    @Override
    public void activate(Entity entity) {
        affect(entity);
        if (!active){
            location.setAreaEffect(null);
        }
    }

    protected void setActive(boolean active){
        this.active = active;
    }

    @Override
    protected abstract void affect(Entity entity);

    @Override
    public abstract AreaEffectType getAreaEffectType();
}
