package View.AreaView;

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
    public void draw(Graphics2D graphics2D){
        if(drawTrap){
            
        }
    }

    @Override
    public void update(){

    }
}
