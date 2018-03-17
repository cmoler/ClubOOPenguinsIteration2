package Model.Entity.Role;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Skill.*;

public class Smasher extends Role {

    private OneHandedWeapon oneHandedWeapon;
    private TwoHandedWeapon twoHandedWeapon;
    private Brawl brawl;

    public Smasher(){
        super();
        oneHandedWeapon = new OneHandedWeapon();
        twoHandedWeapon = new TwoHandedWeapon();
        brawl = new Brawl();
        setMaxMana(20);
    }

    public Smasher(BindWounds bindWounds, Bargain bargain, Observation observation, OneHandedWeapon oneHandedWeapon, TwoHandedWeapon twoHandedWeapon, Brawl brawl){
        super(bindWounds, bargain, observation);
        this.oneHandedWeapon = oneHandedWeapon;
        this.twoHandedWeapon = twoHandedWeapon;
        this.brawl = brawl;
        setMaxMana(20);
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

    @Override
    protected void correctSelected(){
        if(selected < 0) selected = 5;
        if(selected > 5) selected = 0;
    }

    @Override
    public void increaseSkill(int points){
        if(entity.canIncrementSkill()) {
            switch (selected) {
                case 3:
                    addOneHandedWeapon(points);
                    entity.skillPointIncremented();
                    break;
                case 4:
                    addTwoHandedWeapon(points);
                    entity.skillPointIncremented();
                    break;
                case 5:
                    addBrawl(points);
                    entity.skillPointIncremented();
                    break;
                    default:
                        super.increaseSkill(points);
                        break;
            }
        }
    }

    @Override
    public RoleType getRoleType() { return RoleType.Smasher; }

    @Override
    public void save(Saver saver) {
        saver.saveSmasher(this);
    }

}
