package Model.Map.AreaEffect;

import Model.Entity.Entity;

public class KillAreaEffect extends OneShotAreaEffect {

    public KillAreaEffect(){}

    public void affect(Entity entity) {
        entity.takeDamage(entity.getHealth());
        setActive(false);
        // dependent on Entity's default health level
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.KILL;
    }

}

