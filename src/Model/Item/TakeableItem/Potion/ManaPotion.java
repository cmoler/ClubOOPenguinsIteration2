package Model.Item.TakeableItem.Potion;

import Model.Entity.Player;

public class ManaPotion extends Potion {

    private int manaIncrement = 50;

    @Override
    protected void apply(Player entityUsingItem) {
        entityUsingItem.addMana(manaIncrement);
    }
}
