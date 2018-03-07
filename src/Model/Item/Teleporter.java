package Model.Item;

import Model.Entity.Entity;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class Teleporter extends InteractiveItem{

    private String mapID;
    private int locationI;
    private int locationJ;

    public String getMapID() {
      return mapID;
    }

    public int getX() {
      return locationI;
    }

    public int getY() {
      return locationJ;
    }

    public Teleporter(String mapID, int locationI, int locationJ){
        this.mapID = mapID;
        this.locationI = locationI;
        this.locationJ = locationJ;
    }

    @Override
    public void touch(Entity entity) {
        Map nextMap = World.getWorld().getMap(mapID);
        Location nextLocation = nextMap.getLocationIJ(locationI, locationJ);
        entity.teleport(nextLocation);
        World.getWorld().changeCurrentMapTo(nextMap);
        System.out.println("Changing to map: " + mapID);
    }

    public boolean shouldBeRemoved(){
        return false;
    }

    public ItemType getItemType() {
        return ItemType.TELEPORTER;
    }


}
