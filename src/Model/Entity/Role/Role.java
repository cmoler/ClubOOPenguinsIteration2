package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Skill.Bargain;
import Model.Entity.Skill.BindWounds;
import Model.Entity.Skill.Observation;
import javafx.beans.Observable;

public class Role {

    private Entity entity;
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observation observation;

    public Role(Entity e){
        this.entity = e;
    }

    public void bindWounds(){
        bindWounds.use(this.entity);
    }

    public int getBargain(){
        return bargain.getPoints();
    }

    public int getObservation(){
        return observation.getPoints();
    }
}
