package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Utilites.Time;

public class BindWounds extends Skill {

    private double lastUse;
    private double secondsPerUseBeforeDividedBySkill = 60;
    private int healing = 20;

    public BindWounds(){
        super();
    }

    public BindWounds(int points){
        super(points);
    }

    public void use(Entity e){
        if(Time.currentInSeconds() > lastUse + secondsPerUse()) {
            e.heal(healing);
            lastUse = Time.currentInSeconds();
        }
    }

    private double secondsPerUse(){
        return secondsPerUseBeforeDividedBySkill/getPoints();
    }

}
