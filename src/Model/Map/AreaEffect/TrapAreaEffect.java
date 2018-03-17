package Model.Map.AreaEffect;

import Model.Entity.Entity;

import java.util.Random;

public class TrapAreaEffect extends OneShotAreaEffect{
    boolean isVisible;


    public TrapAreaEffect() {
        isVisible = false;
    }

    public void setVisible(int detectChance) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < detectChance ) {
            isVisible = true;
        }
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
        setActive(false);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TRAP;
    }
}
