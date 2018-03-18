package Model.Item.OneShotItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Player;

public class ManaOneShotItem extends OneShotItem{

    public ManaOneShotItem(){
        name = "ManaOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.addMana(35);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveManaOneShotItem(this);
    }
}
