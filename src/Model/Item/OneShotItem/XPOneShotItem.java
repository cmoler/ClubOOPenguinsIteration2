package Model.Item.OneShotItem;

import Model.Entity.Entity;

public class XPOneShotItem extends OneShotItem{

    @Override
    public void touch(Entity entity) {
        entity.gainExperience(15);
    }
}
