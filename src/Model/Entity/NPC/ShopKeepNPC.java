package Model.Entity.NPC;

import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.List;

public class ShopKeepNPC extends NPC {

    private String mapID;
    private int locationI;
    private int locationJ;

    public ShopKeepNPC(String color, String mapID, int locationI, int locationJ) {
        super(color);
        this.mapID = mapID;
        this.locationI = locationI;
        this.locationJ = locationJ;
        this.name = "ShopKeepNPC";
    }

    public void openShop(){
        Map nextMap = World.getWorld().getMap(mapID);
        Location nextLocation = nextMap.getLocationIJ(locationI, locationJ);
        World.getWorld().teleportEntity(player, nextMap, nextLocation);
        World.getWorld().changeCurrentMapTo(nextMap);
    }

    public String getMapID() {
        return mapID;
    }

    public int getLocationI() {
        return locationI;
    }

    public int getLocationJ() {
        return locationJ;
    }

    @Override
    public void talk(){
        openShop();
    }
}
