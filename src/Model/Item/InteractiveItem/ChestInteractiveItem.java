package Model.Item.InteractiveItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Player;
import Model.Item.Item;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TakeableItemGenerator;
import Model.Item.TakeableItem.UseableItem;

public class ChestInteractiveItem extends InteractiveItem {

    private TakeableItem chestLoot;
    boolean isOpened;

    public ChestInteractiveItem(boolean isOpened){
        name = "chestInteractiveItem";
        this.isOpened = isOpened;
        chestLoot = TakeableItemGenerator.getTakeableItemGenerator().getRandomItem();

    }

    public boolean isOpened() {
        return isOpened;
    }

    @Override
    public void touch(Player entity) {
        if (!isOpened){
            UseableItem key = (UseableItem) entity.getItemNamed("key");
            if (key != null){
                key.use(entity, entity.getLocation());
                entity.takeItem(chestLoot);
                isOpened = true;
            }
        }
    }

    @Override
    public String save(Saver saver) {
        return null;
    }
}