package View.StatusView;

import Model.Entity.Equipment;
import View.Viewport;

import java.awt.*;

public class HotBarView extends Viewport {

    private Equipment equipment;

    public HotBarView(Equipment equipment){
        equipment.attach(this);
        this.equipment = equipment;
    }


    @Override
    public void draw(Graphics2D graphics2D){

    }

}
