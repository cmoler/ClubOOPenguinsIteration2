package Model.Item.OneShotItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;

public class XPOneShotItem extends OneShotItem{

    public XPOneShotItem(){
        name = "xpOneShotItem";
    }

    @Override
    public void touch(Player entity) {
        entity.gainExperience(15);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveXPOneShotItem(this);
    }
}
