package Model.Item.OneShotItem;

import Model.Entity.Player;

public class XPOneShotItem extends OneShotItem{

    @Override
    public void touch(Player entity) {
        entity.gainExperience(15);
    }
}
