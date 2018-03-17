package Model.Item.TakeableItem.Key;


import Model.Entity.Player;
import Model.Item.TakeableItem.UseableItem;
import Model.Map.Location;


public class Key extends UseableItem {

    public Key(){
        this.name = "key";
        value = 15;
    }

    @Override
    public void use(Player entityUsingItem, Location locationOfEntity) {
        entityUsingItem.removeItem(this);
    }
}
