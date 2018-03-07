package View.AreaView;

import Configs.AreaSizes;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class ObstacleView extends Viewport {

    private Image obstacleImage;

    public ObstacleView(Image image){
        this.obstacleImage = image;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(obstacleImage, (x* AreaSizes.TERRAIN_WIDTH), (y* AreaSizes.TERRAIN_HEIGHT),
                AreaSizes.OBSTACLE_WIDTH, AreaSizes.OBSTACLE_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }
}
