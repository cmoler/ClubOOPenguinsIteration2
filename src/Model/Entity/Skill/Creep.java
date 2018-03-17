package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Entity.Player;

public class Creep extends Skill {

    public Creep(){
        super();
    }

    public Creep(int points){
        super(points);
    }

    public void use(Player me){
        if(me.getMana() > getManaDecrement())
            me.setVisibleRange(0);
    }

    public boolean isBeingUsed(Entity me){
        if(me.getVisibleRange() == 0)
            return true;
        else
            return false;
    }

    public void turnOff(Entity me){
        me.setVisibleRange(5);
    }

    public int getManaDecrement(){
        return (int)(((double)10)/getPoints());
    }
}
