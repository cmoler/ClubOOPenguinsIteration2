package View.AreaView;

import Configs.AreaSizes;
import Configs.Commons;
import Model.Entity.Entity;
import Model.Map.MapIterator;
import Model.Map.World;
import View.Viewport;
import javafx.util.Pair;

import java.awt.*;

public class MapView extends Viewport {

    private final int VIEW_SIZE_X = 20;
    private final int VIEW_SIZE_Y = 20;

    private int initialI;
    private int initialJ;

    private int offsetI = 0;
    private int offsetJ = 0;

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
            if(mapIterator.currentItem()
                    == entity.getLocation()){
                initialI = mapIterator.getI();
                initialJ = mapIterator.getJ();
            }
        }
        calculatePlayerOffSet();
    }

    public Pair<Integer, Integer> calculateScreenXY(int x, int y){
        return new Pair<Integer, Integer>(x - offsetJ + desiredPlayerXTileOffset, y - offsetI + desiredPlayerYTileOffset);
    }

    @Override
    public void draw(Graphics2D graphics2D){
        MapIterator mapIterator = new MapIterator(World.getWorld().getCurrentMap());
        for(mapIterator.reset(); mapIterator.isValid(); mapIterator.next()){
            if(mapIterator.currentItem() == entity.getLocation()){
                offsetI = mapIterator.getI() - initialI;
                offsetJ = mapIterator.getJ() - initialJ;
            }
        }
        for(Viewport child: children){
            Pair<Integer, Integer> location = calculateScreenXY(child.getLocationX(), child.getLocationY());
            if(child.getLocationX() <= offsetJ + 10 && child.getLocationX() >= offsetJ - 10 && child.getLocationY() <= offsetI + 10 && child.getLocationY() >= offsetI - 10 ) {
                child.draw(graphics2D,location.getKey() ,location.getValue());
            }
        }
    }

    @Override
    public void update(){

    }

}
