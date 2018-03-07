package View.AreaView;

import Configs.AreaSizes;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class TerrainView extends Viewport {

    private Image terrainImage;

    public TerrainView(Image terrainImage){
        this.terrainImage = terrainImage;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(terrainImage, x* AreaSizes.TERRAIN_WIDTH, y* AreaSizes.TERRAIN_WIDTH,
                AreaSizes.TERRAIN_WIDTH, AreaSizes.TERRAIN_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
