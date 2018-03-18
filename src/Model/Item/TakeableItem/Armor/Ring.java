package Model.Item.TakeableItem.Armor;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.TakeableItem.WearableItem;

public class Ring extends WearableItem {

    private int manaIncrease = 25;

    public Ring(){
        super();
        this.name = "ring";
    }
    @Override
    public void putOn(Player player) {
        player.setMaxMana(manaIncrease);
        player.addMana(manaIncrease);
    }

    @Override
    public void takeOff(Player player) {
        player.addMana(-manaIncrease);
        player.setMaxMana(-manaIncrease);
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
