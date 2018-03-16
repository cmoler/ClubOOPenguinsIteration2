package View.AreaView;

import Model.Map.AreaEffect.Trap;
import View.Viewport;

import java.awt.*;

public class TrapView extends Viewport {

    private Trap trap;
    private boolean drawTrap = false;

    public TrapView(Trap trap){
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
