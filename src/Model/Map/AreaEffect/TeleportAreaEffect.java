package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class TeleportAreaEffect extends AreaEffect {

    private String mapID;
    private int locationI;
    private int locationJ;

    public TeleportAreaEffect(String mapID, int locationI, int locationJ) {
        this.mapID = mapID;
        this.locationI = locationI;
        this.locationJ = locationJ;
    }

    public String getMapID() {
        return mapID;
    }

    public int getX() {
        return locationI;
    }

    public int getY() {
        return locationJ;
    }


    @Override
    protected void affect(Entity entity) {
        Map nextMap = World.getWorld().getMap(mapID);
        Location nextLocation = nextMap.getLocationIJ(locationI, locationJ);
        World.getWorld().teleportEntity(entity, nextMap, nextLocation);
        World.getWorld().changeCurrentMapTo(nextMap);
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TELEPORT;
    }
}
