package Model.Item.InteractiveItem;

import Model.Entity.Player;
import Model.Item.Item;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;

public class ChestInteractiveItem extends InteractiveItem {

    private TakeableItem chestLoot;
    boolean isOpened;

    public ChestInteractiveItem(TakeableItem item, boolean isOpened){
        chestLoot = item;
        this.isOpened = isOpened;
    }

    @Override
    public void touch(Player entity) {
        UseableItem key = (UseableItem) entity.getItemNamed("key");
        if (key != null){
            key.use(entity, entity.getLocation());
            entity.takeItem(chestLoot);
            isOpened = true;
        }
    }

}