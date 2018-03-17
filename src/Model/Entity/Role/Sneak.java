package Model.Entity.Role;

import Model.Entity.Entity;
import Model.Entity.Skill.Creep;
import Model.Entity.Skill.DetectAndRemoveTrap;
import Model.Entity.Skill.PickPocket;
import Model.Entity.Skill.RangedWeapon;

public class Sneak extends Role {

    private PickPocket pickPocket = new PickPocket();
    private DetectAndRemoveTrap detectAndRemoveTrap = new DetectAndRemoveTrap();
    private Creep creep = new Creep();
    private RangedWeapon rangedWeapon = new RangedWeapon();

    public int getPickPocket() {
        return pickPocket.getPoints();
    }

    public int getDetectAndRemoveTrap(){
        return detectAndRemoveTrap.getPoints();
    }

    public int getCreep(){
        return creep.getPoints();
    }

    public int getRangedWeapon(){
        return rangedWeapon.getPoints();
    }

    public void addPickPocket(int points) {
        pickPocket.addPoints(points);
    }

    public void addDetectAndRemoveTrap(int points){
        detectAndRemoveTrap.addPoints(points);
    }

    public void addCreep(int points){
        creep.addPoints(points);
    }

    public void addRangedWeapon(int points){
        rangedWeapon.addPoints(points);
    }

    public void pickPocket(Entity otherEntity){
        pickPocket.use(this.entity, otherEntity);
    }

    public void creep(){
        creep.use(this.entity);
    }

    @Override
    public RoleType getRoleType() { return RoleType.Sneak; }
}
