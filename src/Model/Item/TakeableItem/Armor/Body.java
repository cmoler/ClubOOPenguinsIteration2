package Model.Item.TakeableItem.Armor;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Body extends WearableItem {

    private int defenceIncrease = 100;

    public Body(){
        super();
        this.name = "chest";
    }
    @Override
    public void putOn(Player player) {
        player.modifyDefense(defenceIncrease);
    }

    @Override
    public void takeOff(Player player) {
        player.modifyDefense(-defenceIncrease);
    }

    @Override
    public String getSlot() {
        return "body";
    }

    @Override
    public String save(Saver saver) {
        return saver.saveBody(this);
    }
}
