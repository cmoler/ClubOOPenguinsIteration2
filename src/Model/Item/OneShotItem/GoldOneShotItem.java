package Model.Item.OneShotItem;

import Model.Entity.Player;

public class GoldOneShotItem extends OneShotItem {

    @Override
    public void touch(Player entity) {
        entity.addGold(35);
    }
}
