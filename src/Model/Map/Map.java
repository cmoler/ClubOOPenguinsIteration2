package Model.Map;

import Model.Entity.Entity;
import Model.Item.Item;
import Model.Map.Terrain.Ice;
import Model.Visitor.Visitor;
import View.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Model.Map.Direction.*;

public class Map {

    private List<Viewport> observers = new ArrayList<Viewport>();

    public Location[][] getLocations() {
        return locations;
    }

    public EntityLocation getEntityLocationList() {

        return entityLocationList;
    }

    private EntityLocation entityLocationList = new EntityLocation();

    private Location[][] locations;
    private Location defaultLocation;
    private int numRows = 0;
    private int numCols = 0;

    //Used for saving game map
    public int getRows() {
      return numRows;
    }

    public int getCols() {
      return numCols;
    }


    //Default Location is center of map
    public Map(Location[][] allLocations) {
        this.locations = allLocations;
        numRows = this.locations.length;
        numCols = this.locations[0].length;
        this.defaultLocation = locations[numRows / 2][numCols / 2];
        setAdjacencyList();
    }

    //Default Location is specified
    // used in MapBuilder
    public Map(Location[][] locations, Location defaultLocation) {
        this.locations = locations;
        numRows = this.locations.length;
        numCols = this.locations[0].length;
        this.defaultLocation = defaultLocation;
        setAdjacencyList();
    }

    //Create x by y map for testing
    public Map(int x, int y)
    {
        this.locations = new Location[x][y];
        numRows = this.locations.length;
        numCols = this.locations[0].length;

        // for testing: Locations need to be initialized to test Entity movement
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                locations[i][j] = new Location(new Ice(), false, null, new ArrayList<Item>());
            }
        }

        this.defaultLocation = locations[x/2][y/2];
        setAdjacencyList();
    }

    public void removeEntityLocation(Location location){
        entityLocationList.removeEntityLocation(location);
    }

    public void setEntityLocation(Location location, Entity entity){
        entityLocationList.setEntityLocation(location, entity);
    }

    //Update entity location if they are flagged to move
    public void updateEntityLocations(){
        entityLocationList.updateEntityLocations();
    }

    public Entity entityAtLocation(Location location){
        if (entityLocationList.getEntityAtLocation(location) != null)
            return entityLocationList.getEntityAtLocation(location);
        else
            return null;
    }

    public void setAdjacencyList() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                HashMap AdjacentLocations = new HashMap<Direction, Location>();

                //Add NW
                if (i - 1 >= 0 && j - 1 >= 0) {
                    AdjacentLocations.put(NW, locations[i - 1][j - 1]);
                }
                else {
                    AdjacentLocations.put(NW, null);
                }

                //Add N
                if (i - 1 >= 0) {
                    AdjacentLocations.put(N, locations[i - 1][j]);
                }
                else {
                    AdjacentLocations.put(N, null);
                }

                //Add NE
                if (i - 1 >= 0 && j + 1 < numCols) {
                    AdjacentLocations.put(NE, locations[i - 1][j + 1]);
                }
                else {
                    AdjacentLocations.put(NE, null);
                }

                //Add W
                if (j - 1 >= 0) {
                    AdjacentLocations.put(W, locations[i][j - 1]);
                }
                else {
                    AdjacentLocations.put(W, null);
                }

                //Add E
                if (j + 1 < numCols) {
                    AdjacentLocations.put(E, locations[i][j + 1]);
                }
                else {
                    AdjacentLocations.put(E, null);
                }

                //Add SW
                if (i + 1 < numRows && j - 1 >= 0) {
                    AdjacentLocations.put(SW, locations[i + 1][j - 1]);
                }
                else {
                    AdjacentLocations.put(SW, null);
                }

                //Add S
                if (i + 1 < numRows) {
                    AdjacentLocations.put(S, locations[i + 1][j]);
                }
                else {
                    AdjacentLocations.put(S, null);
                }

                //Add SE
                if (i + 1 < numRows && j + 1 < numCols) {
                    AdjacentLocations.put(SE, locations[i + 1][j + 1]);
                }
                else {
                    AdjacentLocations.put(SE, null);
                }

                locations[i][j].setAdjacentLocations(AdjacentLocations);
                locations[i][j].setxCoordinate(j);
                locations[i][j].setyCoordinate(i);
            }
        }


    }

    public Location getDefaultLocation() {
        return defaultLocation;
    }


    public Location getLocationXY(int x, int y) {
        return locations[y][x];
    }

    public Location getLocationIJ(int i, int j) {
        return locations[i][j];
    }

    public void setLocation(int x, int y, Location location) {locations[x][y] = location;}

    public void accept(Visitor v){
        v.visitMap(this);
    }

    public void attach(Viewport viewport) {
        observers.add(viewport);
    }

    public void detach(Viewport viewport) {
        observers.remove(viewport);
    }

    public void notifyView() {
        for (Viewport viewport : observers){
            viewport.update();
        }
    }
}
