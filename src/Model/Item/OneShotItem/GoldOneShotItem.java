package Model.Item.OneShotItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;

public class GoldOneShotItem extends OneShotItem {

    public GoldOneShotItem() {
        name = "goldOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.modifyGold(35);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveGoldOneShotItem(this);
    }
}
