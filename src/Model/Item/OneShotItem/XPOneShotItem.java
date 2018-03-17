package Model.Item.OneShotItem;

import Model.Entity.Player;

public class XPOneShotItem extends OneShotItem{

    public XPOneShotItem(){
        name = "xpOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.gainExperience(15);
    }
}
