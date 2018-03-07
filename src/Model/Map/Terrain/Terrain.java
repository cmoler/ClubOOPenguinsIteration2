package Model.Map.Terrain;

import Model.Entity.EntityType;

public abstract class Terrain {

    public abstract boolean enter(EntityType entityType);

    public abstract TerrainType getTerrainType();

}
