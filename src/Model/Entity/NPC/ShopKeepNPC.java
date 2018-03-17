package Model.Entity.NPC;

import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.List;

public class ShopKeepNPC extends NPC {

    private String mapID;
    private int locationI;
    private int locationJ;

    public ShopKeepNPC(String mapID, int locationI, int locationJ) {
        this.mapID = mapID;
        this.locationI = locationI;
        this.locationJ = locationJ;
    }

    public void openShop(){
        Map nextMap = World.getWorld().getMap(mapID);
        Location nextLocation = nextMap.getLocationIJ(locationI, locationJ);
        World.getWorld().teleportEntity(player, nextMap, nextLocation);
        World.getWorld().changeCurrentMapTo(nextMap);
    }

    @Override
    public void talk(){
        openShop();
    }
}
