package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Skill.Bargain;
import Model.Entity.Skill.BindWounds;
import Model.Entity.Skill.Observation;
import javafx.beans.Observable;

public class Role {

    protected Entity entity;
    protected int selected;

    private BindWounds bindWounds;
    private Bargain bargain;
    private Observation observation;

    public Role(){}

    public void setEntity(Entity entity){
        this.entity = entity;
    }

    public Entity getEntity() { return entity; }

    public int getBindWounds(){
        return bindWounds.getPoints();
    }

    public int getBargain(){
        return bargain.getPoints();
    }

    public int getObservation(){
        return observation.getPoints();
    }

    private void addBindWounds(int points){
        bindWounds.addPoints(points);
    }

    private void addBargain(int points){
        bargain.addPoints(points);
    }

    private void addObservation(int points){
        observation.addPoints(points);
    }

    public void bindWounds(){
        bindWounds.use(this.entity);
    }

    public void scroll(int i){
        selected += i;
        correctSelected();
    }

    protected void correctSelected(){
        if(selected < 0) selected = 2;
        if(selected > 2) selected = 0;
    }

    public void increaseSkill(int points){
        if(entity.canIncrementSkill()) {
            switch (selected) {
                case 0:
                    addBindWounds(points);
                    entity.skillPointIncremented();
                    break;
                case 1:
                    addBargain(points);
                    entity.skillPointIncremented();
                    break;
                case 2:
                    addObservation(points);
                    entity.skillPointIncremented();
                    break;
            }
        }
    }

    public int getSelected() {
        return selected;
    }

    public RoleType getRoleType() { return RoleType.Base; }
}
