package Model.Entity.NPC;

import Model.Entity.Entity;
import Model.Updateable;

public class NPC extends Entity implements Updateable{

    public void beFriends(){
        // makes NPC friendly
    }

    public void pissOff(){
        // makes NPC hostile
    }

    public void fallAsleep(){
        // makes NPC stop updating on update() for certain period of time
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isDone() {
        return false;
    }
}
