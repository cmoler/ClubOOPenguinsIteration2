package Model.Map.AreaEffect;

import Model.Entity.Entity;

public class DamageAreaEffect extends ContinualAreaEffect {

    private int damageAmount;

    public DamageAreaEffect(){
        damageAmount = 10;
    }

    public DamageAreaEffect(int damageAmount){
        this.damageAmount = damageAmount;
    }

    public void affect(Entity entity) {
        entity.takeDamage(damageAmount);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.DAMAGE;
    }
}
