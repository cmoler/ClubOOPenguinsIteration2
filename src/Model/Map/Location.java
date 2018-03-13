package Model.Map;

import Model.Entity.Entity;
import Model.Item.Item;
import Model.Map.AreaEffect.AreaEffect;
import Model.Map.Terrain.Terrain;
import View.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Location {

    private Terrain terrain;
    private boolean obstacle;
    private HashMap<Direction, Location> adjacentLocations;
    private AreaEffect areaEffect;
    private List<Item> items = new ArrayList<>();
    private List<Viewport> observers = new ArrayList<Viewport>();
    int xCoordinate;
    int yCoordinate;

    public Location(Terrain terrain, boolean obstacle, AreaEffect areaEffect, List<Item> items){
        this.terrain = terrain;
        this.obstacle = obstacle;
        this.areaEffect = areaEffect;
        if (this.areaEffect != null)
            this.areaEffect.setLocation(this);
        this.items = items;
    }

    public void setAdjacentLocations(HashMap<Direction, Location> adjacentLocations)
    {
        this.adjacentLocations = adjacentLocations;
    }

    public Location getAdjacentAt(Direction direction){
        return adjacentLocations.get(direction);
    }


    //May be needed
    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    //

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public void activateAreaEffect(Entity entity){
        if (areaEffect != null){
            areaEffect.activate(entity);
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }


    public boolean moveAllowed(Entity entity){
        if (!obstacle && terrain.enter(entity.getEntityType()))
        {
            return true;
        }
        return false;
    }

    public void itemsTouchedBy(Entity entity){
        LocationItemIterator locationItemIterator = getLocationItemIterator();
        for(locationItemIterator.reset();locationItemIterator.hasNext();locationItemIterator.next()){
            locationItemIterator.touchCurrent(entity);
            //if(locationItemIterator.getCurrent().shouldBeRemoved())
            locationItemIterator.removeCurrent();
        }
    }

    public List<Item> getItems(){
        return items;
    }

    public void attach(Viewport viewport){
        observers.add(viewport);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView(){
        for (Viewport viewport : observers){
            viewport.update();
        }
    }

    public LocationItemIterator getLocationItemIterator(){
        return new LocationItemIterator();
    }

    public class LocationItemIterator{
        int index;

        public void reset(){
            index = 0;
        }

        public boolean hasNext(){
            return items.size() > index;
        }

        public void next(){
            index += 1;
        }

        public Item getCurrent(){
            return items.get(index);
        }

        public void touchCurrent(Entity entity) {
            items.get(index).touch(entity);
        }

        public void removeCurrent() { items.remove(index); index--; notifyView();}
    }

    public boolean hasObstacle(){
        return obstacle;
    }
}
