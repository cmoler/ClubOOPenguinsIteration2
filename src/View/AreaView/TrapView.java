package View.AreaView;

import Configs.AreaSizes;
import Configs.ImagesInfo;
import Model.Map.AreaEffect.TrapAreaEffect;
import View.Viewport;

import java.awt.*;

public class TrapView extends Viewport {

    private TrapAreaEffect trap;
    private boolean drawTrap = false;

    public TrapView(TrapAreaEffect trap){
        this.trap = trap;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        if(drawTrap){
            graphics2D.drawImage(ImagesInfo.TRAP_IMAGE, x* AreaSizes.TERRAIN_WIDTH, y* AreaSizes.TERRAIN_HEIGHT,
                AreaSizes.AREA_EFFECT_WIDTH, AreaSizes.AREA_EFFECT_HEIGHT,this );
        }
    }

    @Override
    public void update(){
        drawTrap = trap.isVisible();
    }
}
