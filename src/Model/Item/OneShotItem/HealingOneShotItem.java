package Model.Item.OneShotItem;

import Model.Entity.Player;

public class HealingOneShotItem extends OneShotItem{

    @Override
    public void touch(Player entity) {
        entity.heal(10);
    }
}
