package Model.Item.OneShotItem;

import Model.Entity.Player;

public class GoldOneShotItem extends OneShotItem {

    public GoldOneShotItem() {
        name = "goldOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.modifyGold(35);
    }
}
