package View.AreaView.ItemView;


import Configs.AreaSizes;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class ItemView extends Viewport {

    private Image itemImage;

    public ItemView(){

    }

    public ItemView(Image image){
        this.itemImage = image;
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(itemImage, (x* AreaSizes.TERRAIN_WIDTH)+(AreaSizes.TERRAIN_WIDTH/4), (y* AreaSizes.TERRAIN_HEIGHT)+(AreaSizes.TERRAIN_HEIGHT/4),
                AreaSizes.ITEM_WIDTH, AreaSizes.ITEM_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    }
