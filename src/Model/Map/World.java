package Model.Map;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Saveable;
import View.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World implements Saveable{

    private List<Viewport> observers = new ArrayList<Viewport>();

    private static World instance = null;
    private Map currentMap;

    public HashMap<String, Map> getMaps() {
        return maps;
    }

    private HashMap<String,Map> maps = new HashMap<String,Map>();

    protected World(){
        // Can't instantiate
    }

    public static World getWorld(){
        if (instance == null)
        {
            instance = new World();
        }
        return instance;
    }

    public void changeCurrentMapTo(Map map){
        currentMap = map;
    }

    public void teleportEntity(Entity entity, Map nextMap, Location nextLocation){
        System.out.println("current map:" + currentMap.getMapID());
        System.out.println("teleport to:" + nextMap.getMapID());
        System.out.println("teleport to next loction:" + nextLocation);
        currentMap.removeEntityLocation(entity.getLocation());
        nextMap.setEntityLocation(nextLocation, entity);
    }

    public Map getCurrentMap(){
        return currentMap;
    }

    public void addMap(String mapID, Map map){
        map.setMapID(mapID);
        maps.put(mapID, map);
    }

    public Map getMap(String mapID){
        return maps.get(mapID);
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

    @Override
    public void save(Saver saver) {
        saver.serializeWorld(this);
    }
}