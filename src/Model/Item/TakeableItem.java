package Model.Item;

import Model.Entity.Entity;

public class TakeableItem extends Item{

    @Override
    public void touch(Entity entity) {
        entity.getInventory().addItem(this);
    }

    public boolean shouldBeRemoved(){
        return true; // always true?
    }
    public ItemType getItemType() {
        return ItemType.TAKEABLE;
    }

}
