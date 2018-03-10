package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Skill.Bargain;
import Model.Entity.Skill.BindWounds;
import Model.Entity.Skill.Observation;
import javafx.beans.Observable;

public class Role {

    protected Entity entity;
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observation observation;

    public Role(Entity e){
        this.entity = e;
    }

    public int getBindWounds(){
        return bindWounds.getPoints();
    }

    public int getBargain(){
        return bargain.getPoints();
    }

    public int getObservation(){
        return observation.getPoints();
    }

    public void addBindWounds(int points){
        bindWounds.addPoints(points);
    }

    public void addBargain(int points){
        bargain.addPoints(points);
    }

    public void addObservation(int points){
        observation.addPoints(points);
    }

    public void bindWounds(){
        bindWounds.use(this.entity);
    }
}
