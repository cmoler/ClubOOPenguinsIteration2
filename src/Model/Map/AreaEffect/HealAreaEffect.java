package Model.Map.AreaEffect;

import Model.Entity.Entity;

public class HealAreaEffect extends ContinualAreaEffect {

    private int healAmount;

    public HealAreaEffect() {
        healAmount = 10;
    }

    public HealAreaEffect(int healAmount){
        this.healAmount = healAmount;
    }

    public void affect(Entity entity) {
        entity.heal(healAmount);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.HEAL;
    }

}
