package Model.Map.AreaEffect;

import Model.Entity.Entity;
import View.AreaView.TrapView;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrapAreaEffect extends OneShotAreaEffect{
    boolean isVisible;
    private List<Viewport> observers = new ArrayList<Viewport>();


    public TrapAreaEffect() {
        isVisible = false;
    }

    public void setVisible(int detectChance) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < detectChance ) {
            isVisible = true;
            notifyView();
        }
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void deactivate(){
        location.setAreaEffect(null);
    }

    @Override
    protected void affect(Entity entity) {
        entity.takeDamage(entity.getMaxHealth()/2);
        isVisible = true;
        setActive(false);
        notifyView();
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TRAP;
    }

    public void attach(TrapView trapView) {
        observers.add(trapView);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView(){
        for (Viewport viewport : observers){
            viewport.update();
        }
    }
}
