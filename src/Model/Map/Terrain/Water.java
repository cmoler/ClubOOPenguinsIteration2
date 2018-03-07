package Model.Map.Terrain;

import Model.Entity.EntityType;

public class Water extends Terrain{
    @Override
    public boolean enter(EntityType entityType) {

        //Implemented as switch so that future entityTypes can be added
        switch(entityType){
            case WATER:
                return true;
            default:
                return false;
        }
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.WATER;
    }
}
