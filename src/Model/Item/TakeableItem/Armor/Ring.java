package Model.Item.TakeableItem.Armor;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Ring extends WearableItem {

    public Ring(){
        super();
        this.name = "ring";
    }
    @Override
    public void putOn(Player player) {

    }

    @Override
    public void takeOff(Player player) {

    }

    @Override
    public String getSlot() {
        return "ring";
    }

    @Override
    public String save(Saver saver) {
        return saver.saveRing(this);
    }
}
