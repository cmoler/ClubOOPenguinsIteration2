package Model.Item.OneShotItem;

import Model.Entity.Entity;

public class GoldOneShotItem extends OneShotItem {

    @Override
    public void touch(Entity entity) {
        entity.addGold(35);
    }
}
