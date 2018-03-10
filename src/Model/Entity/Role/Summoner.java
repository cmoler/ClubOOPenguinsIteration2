package Model.Entity.Role;

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

    public Summoner(Entity e){
        super(e);
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

}
