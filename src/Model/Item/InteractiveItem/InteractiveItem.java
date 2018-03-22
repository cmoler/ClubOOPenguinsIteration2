package Model.Item.InteractiveItem;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.Item;

public abstract class InteractiveItem extends Item {

    @Override
    public void touch(Player entity) {

    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}