package View.AreaView;


import Configs.AreaSizes;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class DecalView extends Viewport {

    private Image decalImage;

    public DecalView(Image image){
        this.decalImage = image;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(decalImage, x* AreaSizes.TERRAIN_WIDTH + AreaSizes.TERRAIN_WIDTH/4, y* AreaSizes.TERRAIN_HEIGHT + AreaSizes.TERRAIN_HEIGHT/4,
                AreaSizes.DECAL_WIDTH, AreaSizes.DECAL_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
