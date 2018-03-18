package Model.Item.TakeableItem.Armor;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Leg extends WearableItem {

    private int defenceIncrease = 75;

    public Leg(){
        super();
        this.name = "legs";
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
        return "legs";
    }

    @Override
    public String save(Saver saver) {
        return saver.saveLeg(this);
    }
}
