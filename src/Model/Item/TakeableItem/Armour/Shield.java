package Model.Item.TakeableItem.Armour;

import Model.Entity.Entity;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.WearableItem;

public class Shield extends WearableItem{

    private double defenseIncrease = 50;

    @Override
    public void putOn(Entity entity) {
        entity.modifyDefense(defenseIncrease);
    }

    @Override
    public void takeOff(Entity entity) {
        entity.modifyDefense(-defenseIncrease);
    }
}
