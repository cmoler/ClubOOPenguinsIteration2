package Model.Item.OneShotItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;

public class HealingOneShotItem extends OneShotItem{

    public HealingOneShotItem(){
        name = "healingOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.heal(10);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveHealingOneShotItem(this);
    }
}
