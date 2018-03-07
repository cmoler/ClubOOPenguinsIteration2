package Model.Map;

import View.AreaView.MapView;
import View.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {

    private List<Viewport> observers = new ArrayList<Viewport>();

    public static World instance = null;
    private Map currentMap;
    private Viewport viewport;
    private HashMap<String,Map> maps = new HashMap<String,Map>();
    private HashMap<Map,MapView> mapViews = new HashMap<Map,MapView>();

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

    public void setViewport(Viewport viewport){
        this.viewport = viewport;
    }

    public void changeCurrentMapTo(Map map){
        if(viewport!=null)viewport.addToFront(mapViews.get(map));
        if(currentMap!=null)viewport.remove(mapViews.get(currentMap));
        currentMap = map;
    }

    public Map getCurrentMap(){
        return currentMap;
    }

    public void addMap(String mapID, Map map, MapView mapView){
        maps.put(mapID, map);
        mapViews.put(map, mapView);
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

    public void notifyView(MapView last, MapView current){
        for (Viewport viewport : observers){
            viewport.updateMap(last, current);
        }
    }
}