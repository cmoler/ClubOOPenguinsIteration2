package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Item.Item;
import Model.Map.EntityLocation;

import java.util.ArrayList;

public class Observation extends Skill {
    public Observation(int points){
        super(points);
    }

    private Location goToTile(Location location, int moveX, int moveY) {
        if(moveX < 0) {
            moveX = Math.abs(moveX);
            for (int i = 0; i < moveX; i++)
                location = location.getAdjacentAt(Direction.S);
        }
        else
            for (int i = 0; i < moveX; i++)
                location = location.getAdjacentAt(Direction.N);

        if(moveY < 0) {
            moveY = Math.abs(moveY);
            for (int i = 0; i < moveY; i++)
                location = location.getAdjacentAt(Direction.W);
        }
        else
            for (int i = 0; i < moveY; i++)
                location = location.getAdjacentAt(Direction.E);

        return location;
    }

    public ArrayList<String> getTileItems(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        ArrayList<String> itemArr = new ArrayList<>();

        for (Item i : location.getItems())
            itemArr.add(i.getName());

        return itemArr;
    }

    public String getTileTerrain(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        return location.getTerrain().getTerrainType().toString();
    }

    public String getAreaEffect(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        return location.getAreaEffect().getAreaEffectType().toString();
    }

    public int getEntityHealth(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        return el.getEntityAtLocation(location).getHealth();
    }

    public int getEntityMaxHealth(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        return el.getEntityAtLocation(location).getMaxHealth();
    }

    public int getEntityLevel(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        return el.getEntityAtLocation(location).getLevel();
    }

    public String getEntityType(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        return el.getEntityAtLocation(location).getEntityType().toString();
    }


}
