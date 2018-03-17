package Model.Item.TakeableItem.BoonItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Location;

import java.util.Random;

public class IncreaseMaxHealth extends BoonItem {

    public IncreaseMaxHealth(){
        super();
        this.name = "increaseMaxHealth";
    }

    private int manaNeeded = 10;

    private int maxHealthIncrement = 40;

    @Override
    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    protected void apply(Entity entityUsingItem) {
        entityUsingItem.modifyMaxHealth(maxHealthIncrement);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveIncreaseMaxHealth(this);
    }
}
