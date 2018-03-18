package Model.Item.InteractiveItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.Item;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;

public class ChestInteractiveItem extends InteractiveItem {

    private TakeableItem chestLoot;
    boolean isOpened;

    public ChestInteractiveItem(TakeableItem item, boolean isOpened){
        name = "chestInteractiveItem";
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

    @Override
    public String save(Saver saver) {
        return saver.saveChestInteractiveItem(this);
    }
}