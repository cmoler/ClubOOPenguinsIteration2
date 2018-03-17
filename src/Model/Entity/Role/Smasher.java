package Model.Entity.Role;

import Model.Entity.Skill.Brawl;
import Model.Entity.Skill.OneHandedWeapon;
import Model.Entity.Skill.TwoHandedWeapon;

public class Smasher extends Role {

    private OneHandedWeapon oneHandedWeapon = new OneHandedWeapon();
    private TwoHandedWeapon twoHandedWeapon = new TwoHandedWeapon();
    private Brawl brawl = new Brawl();

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
}
