package Model.Item.OneShotItem;

import Model.Entity.Entity;

public class ManaOneShotItem extends OneShotItem{

    @Override
    public void touch(Entity entity) {
        entity.addMana(35);
    }
}
