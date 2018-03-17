package Model.Entity.Role;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Skill.Creep;
import Model.Entity.Skill.DetectAndRemoveTrap;
import Model.Entity.Skill.PickPocket;
import Model.Entity.Skill.RangedWeapon;
import Model.Map.AreaEffect.AreaEffect;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.AreaEffect.TrapAreaEffect;
import Model.Map.Direction;
import Model.Map.Location;

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

    public void pickPocket(){
        pickPocket.use(this.entity);
    }

    public void creep(){
        creep.use(this.entity);
    }

    @Override
    public void activateTrait(Location location){

        //Detect Traps
        int detectChance = getDetectAndRemoveTrap()*10;

        for (Direction dir : Direction.values()){
            Location adjLocation = location.getAdjacentAt(dir);
            AreaEffect areaEffect = adjLocation.getAreaEffect();
            if (areaEffect.getAreaEffectType() ==  AreaEffectType.TRAP){
                ((TrapAreaEffect) areaEffect).setVisible(detectChance);
            }
        }

        if(creep.isBeingUsed(this.entity)){
            if(this.entity.getMana() > creep.getManaDecrement())
                this.entity.addMana(-creep.getManaDecrement());
            else
                creep.turnOff(this.entity);
        }

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
                    addPickPocket(points);
                    entity.skillPointIncremented();
                    break;
                case 4:
                    addDetectAndRemoveTrap(points);
                    entity.skillPointIncremented();
                    break;
                case 5:
                    addCreep(points);
                    entity.skillPointIncremented();
                    break;
                case 6:
                    addRangedWeapon(points);
                    entity.skillPointIncremented();
                    break;
                default:
                    super.increaseSkill(points);
                    break;
            }
        }
    }

    @Override
    public RoleType getRoleType() { return RoleType.Sneak; }

    @Override
    public void save(Saver saver) {
        saver.saveSneak(this);
    }
}
