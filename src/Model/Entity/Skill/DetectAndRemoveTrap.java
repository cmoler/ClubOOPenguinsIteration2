package Model.Entity.Skill;

import Model.Entity.Player;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.AreaEffect.TrapAreaEffect;
import Model.Map.Direction;
import Model.Map.Location;

import java.util.Random;

public class DetectAndRemoveTrap extends Skill {

    public DetectAndRemoveTrap(){
        super();
    }

    public DetectAndRemoveTrap(int points){
        super(points);
    }

    private int manaCost = 10;

    public void use(Player player) {
        Direction directionFacing = player.getDirectionFacing();
        Location location = player.getLocation().getAdjacentAt(directionFacing);
        if (location != null) {
            if (location.getAreaEffect() != null) {

                if (location.getAreaEffect().getAreaEffectType() == AreaEffectType.TRAP) {

                    if (player.getMana() >= manaCost && ((TrapAreaEffect) location.getAreaEffect()).isVisible()){
                        Random random = new Random();
                        int chance = random.nextInt(100);
                        if (chance < getPoints()*10) {
                            ((TrapAreaEffect) location.getAreaEffect()).deactivate();
                        }
                        player.addMana(-manaCost);
                    }
                }
            }
        }
    }
}
