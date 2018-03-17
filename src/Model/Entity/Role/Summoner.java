package Model.Entity.Role;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Skill.Bane;
import Model.Entity.Skill.Boon;
import Model.Entity.Skill.Enchantment;
import Model.Entity.Skill.Staff;

public class Summoner extends Role {

    private Enchantment enchantment = new Enchantment();
    private Boon boon = new Boon();
    private Bane bane = new Bane();
    private Staff staff = new Staff();

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
