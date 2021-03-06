package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Skill.Bargain;
import Model.Entity.Skill.BindWounds;
import Model.Entity.Skill.Observation;
import Model.Map.Location;
import Model.Saveable;
import javafx.beans.Observable;

import java.util.ArrayList;

public abstract class Role implements Saveable{

    protected Player entity;
    protected int selected;
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observation observation;

    private int maxMana;

    public Role(){
        bindWounds = new BindWounds();
        bargain = new Bargain();
        observation = new Observation();
    }

    public Role(BindWounds bindWounds, Bargain bargain, Observation observation){
        this.bindWounds = bindWounds;
        this.bargain = bargain;
        this.observation = observation;
    }



    public void setEntity(Player entity){
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

    public Observation ObservationSkill(){
        return observation;
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

    public ArrayList<String> observation() {return observation.use(this.entity); }

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

    public void activateTrait(Location location){}

    public int getMaxMana() {return maxMana;}

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
}
