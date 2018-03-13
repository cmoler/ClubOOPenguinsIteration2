package Model.Item.OneShotItem;

import Model.Entity.Entity;

public class HealingOneShotItem extends OneShotItem{

    @Override
    public void touch(Entity entity) {
        entity.heal(10);
    }
}
