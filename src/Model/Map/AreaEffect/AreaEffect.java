package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Map.Location;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AreaEffect {

    private AtomicInteger totalEntitiesOn = new AtomicInteger(0);
    protected Location location;

    public boolean isActive(){
        return totalEntitiesOn.get() > 0;
    }

    public void activate(Entity entity){
        AffectEntityThread thread = new AffectEntityThread(entity);
        thread.start();
    }

    public void setLocation(Location location){
        this.location = location;
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
//                System.out.println("Health: " + entity.getHealth()); // for testing
                try {
                    Thread.sleep(effectIntervalInSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            totalEntitiesOn.decrementAndGet();
        }
    }

    public abstract AreaEffectType getAreaEffectType();
}


