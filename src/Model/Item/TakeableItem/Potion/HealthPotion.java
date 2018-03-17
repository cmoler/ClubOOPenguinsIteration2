package Model.Item.TakeableItem.Potion;

import Model.Entity.Player;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;

public class HealthPotion extends Potion {

    public HealthPotion(){
        this.name = "healthPotion";
    }

    private int healthIncrement = 50;

    protected void apply(Player entityUsingItem){
        entityUsingItem.heal(healthIncrement);
    }
}
