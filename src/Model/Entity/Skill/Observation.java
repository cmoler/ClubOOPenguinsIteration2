package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Item.Item;
import Model.Map.EntityLocation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Observation extends Skill {

    private int probablity;

    public Observation(int points){
        super(points);
        probablity = points * 9 + 9;
    }

    public Observation(){
        super();
        probablity = 0 * 9 + 9;
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

        if (ThreadLocalRandom.current().nextInt(0, 101) <= probablity)
            return el.getEntityAtLocation(location).getHealth();
        else
            return ThreadLocalRandom.current().nextInt(1, 200);
    }

    public int getEntityMaxHealth(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        if (ThreadLocalRandom.current().nextInt(0, 101) <= probablity)
            return el.getEntityAtLocation(location).getMaxHealth();
        else
            return ThreadLocalRandom.current().nextInt(1, 200);
    }

    public int getEntityLevel(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        if(ThreadLocalRandom.current().nextInt(0, 101) <= probablity)
            return el.getEntityAtLocation(location).getLevel();
        else
            return ThreadLocalRandom.current().nextInt(1, 51);
    }

    public String getEntityType(Location location, int moveX, int moveY) {
        location = goToTile(location, moveX, moveY);

        EntityLocation el =  new EntityLocation();

        if(ThreadLocalRandom.current().nextInt(0, 101) <= probablity)
            return el.getEntityAtLocation(location).getEntityType().toString();
        else
            return "Unidentified";
    }


}
