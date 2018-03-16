package Model.Map.AreaEffect;

import Model.Entity.Entity;

public class Trap extends OneShotAreaEffect{
    boolean isVisible;


    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void deactivate(){
        location.setAreaEffect(null);
    }

    @Override
    protected void affect(Entity entity) {
        entity.takeDamage(entity.getMaxHealth()/2);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TRAP;
    }
}
