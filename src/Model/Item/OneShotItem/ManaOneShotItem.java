package Model.Item.OneShotItem;

import Model.Entity.Entity;
import Model.Entity.Player;

public class ManaOneShotItem extends OneShotItem{

    @Override
    public void touch(Player entity) {
        entity.addMana(35);
    }
}
