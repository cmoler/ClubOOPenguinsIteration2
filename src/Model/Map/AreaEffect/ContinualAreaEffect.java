package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Map.Location;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ContinualAreaEffect extends AreaEffect {

    private AtomicInteger totalEntitiesOn = new AtomicInteger(0);

    public boolean isActive(){
        return totalEntitiesOn.get() > 0;
    }

    public void activate(Entity entity){
        AffectEntityThread thread = new AffectEntityThread(entity);
        thread.start();
    }

    protected abstract void affect(Entity entity);

    private class AffectEntityThread extends Thread {

        private long effectIntervalInSeconds = 1;
        private Entity entity;

        AffectEntityThread(Entity entity) {
            this.entity = entity;
        }

        public void run() {
            totalEntitiesOn.getAndAdd(1);
            while (entity.getLocation() == location) {
                affect(entity);
//                System.out.println("Health: " + entity.xml.getHealth()); // for testing
                try {
                    Thread.sleep(effectIntervalInSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            totalEntitiesOn.decrementAndGet();
        }
    }

    @Override
    public abstract AreaEffectType getAreaEffectType();
}


