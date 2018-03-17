package Model.Entity.Role;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Skill.*;

public class Summoner extends Role {

    private Enchantment enchantment;
    private Boon boon;
    private Bane bane;
    private Staff staff;

    public Summoner(){
        super();
        enchantment = new Enchantment();
        boon = new Boon();
        bane = new Bane();
        staff = new Staff();
    }

    public Summoner(BindWounds bindWounds, Bargain bargain, Observation observation, Enchantment enchantment, Boon boon, Bane bane, Staff staff ){
        super(bindWounds, bargain, observation);
        this.enchantment = enchantment;
        this.boon = boon;
        this.bane = bane;
        this.staff = staff;
    }

    public int getEnchantment(){
        return enchantment.getPoints();
    }

    public int getBoon(){
        return boon.getPoints();
    }

    public int getBane(){
        return bane.getPoints();
    }

    public int getStaff(){
        return staff.getPoints();
    }

    public void addEnchantment(int points){
        enchantment.addPoints(points);
    }

    public void addBoon(int points){
        boon.addPoints(points);
    }

    public void addBane(int points){
        bane.addPoints(points);
    }

    public void addStaff(int points){
        staff.addPoints(points);
    }

    @Override
    public void save(Saver saver) {
        saver.saveSummoner(this);
    }
    @Override
    protected void correctSelected(){
        if(selected < 0) selected = 6;
        if(selected > 6) selected = 0;
    }

    @Override
    public void increaseSkill(int points){
        if(entity.canIncrementSkill()) {
            switch (selected) {
                case 3:
                    addEnchantment(points);
                    entity.skillPointIncremented();
                    break;
                case 4:
                    addBoon(points);
                    entity.skillPointIncremented();
                    break;
                case 5:
                    addBane(points);
                    entity.skillPointIncremented();
                    break;
                case 6:
                    addStaff(points);
                    entity.skillPointIncremented();
                    break;
                default:
                    super.increaseSkill(points);
                    break;
            }
        }
    }

    @Override
    public RoleType getRoleType() { return RoleType.Summoner; }

}
