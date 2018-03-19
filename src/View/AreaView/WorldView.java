package View.AreaView;

import Model.Map.Map;
import Model.Map.World;
import View.Viewport;

import java.awt.*;
import java.util.HashMap;

public class WorldView extends Viewport{

    private HashMap<Map,MapView> mapViews;
    private MapView currentView;

    public WorldView(HashMap<Map,MapView> mapViews){
        this.mapViews = mapViews;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        currentView = mapViews.get(World.getWorld().getCurrentMap());
        if(currentView != null) currentView.draw(graphics2D);
        else update();
    }

}
