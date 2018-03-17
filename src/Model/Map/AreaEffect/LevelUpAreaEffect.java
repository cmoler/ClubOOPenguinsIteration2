package Model.Map.AreaEffect;

import Model.Entity.Entity;

public class LevelUpAreaEffect extends OneShotAreaEffect {

    public LevelUpAreaEffect(){}

    public void affect(Entity entity) {
        int experienceToAdd = entity.getExperienceForNextLevel() - entity.getExperience();
        entity.gainExperience(experienceToAdd+1);
        setActive(false);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.LEVELUP;
    }

}
