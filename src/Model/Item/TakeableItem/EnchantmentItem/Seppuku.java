package Model.Item.TakeableItem.EnchantmentItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.NPC.NPC;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.Random;

public class Seppuku extends EnchantmentItem {

    public Seppuku(){
        super();
        this.name = "seppuku";
    }

    private int manaNeeded = 20;

    protected void apply(Entity entityAtTarget){
        ((NPC) entityAtTarget).takeDamage(100);
    }

    protected int getManaNeeded(){
        return manaNeeded;
    }

    @Override
    public String save(Saver saver) {
        return saver.saveSeppuku(this);
    }
}
