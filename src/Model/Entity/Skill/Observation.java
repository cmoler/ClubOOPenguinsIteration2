package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Item.Item;
import Model.Map.EntityLocation;
import Model.Map.World;

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
            return el.getEntityAtLocation(location).getEntityType().name();
        else
            return "Unidentified";
    }


    public ArrayList<String> use(Player entity) {
        Location location = entity.getLocation();
        EntityLocation el = World.getWorld().getCurrentMap().getEntityLocationList();
        ArrayList<String> entityInfo= new ArrayList<>();

        System.out.println("Observation: " + entity.getRole().getObservation());

        for(int i = 0; i < 3; i++) {
            if(location != null) {
                location = location.getAdjacentAt(entity.getDirectionFacing());
                if(location != null && el.getEntityAtLocation(location) != null) {
                    if(ThreadLocalRandom.current().nextInt(0, 101) <= entity.getRole().getObservation() * 9 + 9) {
                        entityInfo.add(" ");
                        entityInfo.add("Sucess!");
                        entityInfo.add("Name: " + el.getEntityAtLocation(location).getEntityType().name());
                        entityInfo.add("Health: " + Integer.toString(el.getEntityAtLocation(location).getHealth()));
                        entityInfo.add("Max Health: " + Integer.toString(el.getEntityAtLocation(location).getMaxHealth()));
                        entityInfo.add("Level: " + Integer.toString(el.getEntityAtLocation(location).getLevel()));
                        break;
                    }

                    else {
                        entityInfo.add(" ");
                        entityInfo.add("Failure");
                        entityInfo.add("Name: Unavailable");
                        entityInfo.add("Health: " + ThreadLocalRandom.current().nextInt(0, 1000));
                        entityInfo.add("Max Health: " + ThreadLocalRandom.current().nextInt(0, 1000));
                        entityInfo.add("Level: " + ThreadLocalRandom.current().nextInt(0, 100));
                        break;
                    }
                }
                else
                    entityInfo.add(" ");
            }
        }

        return entityInfo;

    }
}
