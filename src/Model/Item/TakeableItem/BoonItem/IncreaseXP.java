package Model.Item.TakeableItem.BoonItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

import java.util.Random;

public class IncreaseXP extends BoonItem {

    public IncreaseXP(){
        super();
        this.name = "increaseLevel";
    }

    private int manaNeeded = 100;
    private int XPIncrement = 40;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Entity entityUsingItem) {
        entityUsingItem.gainExperience(XPIncrement);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveIncreaseXP(this);
    }
}
