package Model.Item.TakeableItem.Potion;

import Model.Entity.Player;

public class XPPotion extends Potion {

    public XPPotion(){
        super();
        this.name = "xpPotion";
    }

    private int XPIncrement = 75;

    @Override
    protected void apply(Player entityUsingItem) {
        entityUsingItem.gainExperience(XPIncrement);
    }
}
