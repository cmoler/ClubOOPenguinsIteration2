package Model.Item.TakeableItem.Potion;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;

public class HealthPotion extends Potion {

    private int healthIncrement = 50;

    protected void apply(Player entityUsingItem){
        entityUsingItem.heal(healthIncrement);
    }

    @Override
    public String save(Saver saver) {
        return saver.saveHealthPotion(this);
    }
}
