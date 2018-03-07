package View.AreaView;


import Configs.AreaSizes;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class AreaEffectView extends Viewport {

    private Image areaEffectImage;

    public AreaEffectView(Image image){
        this.areaEffectImage = image;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(areaEffectImage, x* AreaSizes.TERRAIN_WIDTH, y* AreaSizes.TERRAIN_HEIGHT,
                AreaSizes.AREA_EFFECT_WIDTH, AreaSizes.AREA_EFFECT_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
