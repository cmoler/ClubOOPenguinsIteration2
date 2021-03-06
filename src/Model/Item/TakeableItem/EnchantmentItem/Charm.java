package Model.Item.TakeableItem.EnchantmentItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.NPC.NPC;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.Enchantment;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.Random;

public class Charm extends EnchantmentItem {

    public Charm(){
        super();
        this.name = "charm";
    }

    private int manaNeeded = 5;

    protected void apply(Entity entityAtTarget){
        ((NPC) entityAtTarget).beFriends();
    }

    protected int getManaNeeded() {
        return manaNeeded;
    }

    @Override
    public String save(Saver saver) {
        return saver.saveCharm(this);
    }
}
