package Model.Map.AreaEffect;

import Model.Entity.Entity;

public abstract class OneShotAreaEffect extends AreaEffect {

    @Override
    public void activate(Entity entity) {
        affect(entity);
        location.setAreaEffect(null);
    }

    @Override
    protected abstract void affect(Entity entity);

    @Override
    public abstract AreaEffectType getAreaEffectType();
}
