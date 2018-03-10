package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Skill.Brawl;
import Model.Entity.Skill.OneHandedWeapon;
import Model.Entity.Skill.TwoHandedWeapon;

public class Smasher extends Role {

    private OneHandedWeapon oneHandedWeapon = new OneHandedWeapon();
    private TwoHandedWeapon twoHandedWeapon = new TwoHandedWeapon();
    private Brawl brawl = new Brawl();

    public Smasher(Entity e){
        super(e);
    }

    public int getOneHandedWeapon(){
        return oneHandedWeapon.getPoints();
    }

    public int getTwoHandedWeapon(){
        return twoHandedWeapon.getPoints();
    }

    public int getBrawl(){
        return brawl.getPoints();
    }

    public void addOneHandedWeapon(int points){
        oneHandedWeapon.addPoints(points);
    }

    public void addTwoHandedWeapon(int points){
        twoHandedWeapon.addPoints(points);
    }

    public void addBrawl(int points){
        brawl.addPoints(points);
    }
}
