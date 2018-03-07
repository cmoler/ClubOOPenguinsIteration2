package Model.Item;

import Model.Entity.Entity;

public class InteractiveItem extends Item{
    @Override
    public void touch(Entity entity) {

    }

    public boolean shouldBeRemoved(){
        return false;
    }


    public ItemType getItemType() {
        return ItemType.INTERACTIVE;
    }

}