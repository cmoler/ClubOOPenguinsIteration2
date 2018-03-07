package View.AreaView;

import Configs.AreaSizes;
import Configs.Commons;
import Model.Entity.Entity;
import Model.Map.MapIterator;
import Model.Map.World;
import View.Viewport;

import java.awt.*;

public class MapView extends Viewport {

    private final int VIEW_SIZE_X = 20;
    private final int VIEW_SIZE_Y = 20;

    private int initialI;
    private int initialJ;

    private int desiredPlayerXTileOffset;
    private int desiredPlayerYTileOffset;

    private Entity entity;

    public MapView() {
    }

    private void calculatePlayerOffSet(){
        double DESIRED_PLAYER_X_RATIO = 265.0f/768.0f;
        double DESIRED_PLAYER_Y_RATIO = 165.0f/501.0f;

        double DESIRED_PLAYER_X = DESIRED_PLAYER_X_RATIO * Commons.SCREEN_WIDTH;
        double DESIRED_PLAYER_Y = DESIRED_PLAYER_Y_RATIO * Commons.SCREEN_HEIGHT;

        int desiredPlayerXTile = (int)(DESIRED_PLAYER_X / AreaSizes.TERRAIN_WIDTH);
        int desiredPlayerYTile = (int)(DESIRED_PLAYER_Y / AreaSizes.TERRAIN_HEIGHT);

        desiredPlayerXTileOffset = desiredPlayerXTile - initialJ;
        desiredPlayerYTileOffset = desiredPlayerYTile - initialI;
    }

    public void setEntity(Entity entity){
        this.entity = entity;
        MapIterator mapIterator = new MapIterator(World.getWorld().getCurrentMap());
        for(mapIterator.reset(); mapIterator.isValid(); mapIterator.next()){
            if(mapIterator.currentItem() == entity.getLocation()){
                initialI = mapIterator.getI();
                initialJ = mapIterator.getJ();
            }
        }
        calculatePlayerOffSet();
    }

    @Override
    public void draw(Graphics2D graphics2D){
        int offsetI = 0;
        int offsetJ = 0;
        MapIterator mapIterator = new MapIterator(World.getWorld().getCurrentMap());
        for(mapIterator.reset(); mapIterator.isValid(); mapIterator.next()){
            if(mapIterator.currentItem() == entity.getLocation()){
                offsetI = mapIterator.getI() - initialI;
                offsetJ = mapIterator.getJ() - initialJ;
            }
        }
        for(Viewport child: children){
            if(child.getLocationX() <= offsetJ + 10 && child.getLocationX() >= offsetJ - 10&&
                    child.getLocationY() <= offsetI + 10 && child.getLocationY() >= offsetI - 10 ){
                child.draw(graphics2D, child.getLocationX() - offsetJ + desiredPlayerXTileOffset, child.getLocationY() - offsetI + desiredPlayerYTileOffset);
            }
        }
    }

}
