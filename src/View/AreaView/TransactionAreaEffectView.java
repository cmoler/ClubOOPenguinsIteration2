package View.AreaView;

import Configs.AreaSizes;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.AreaEffect.TransactionAreaEffect;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class TransactionAreaEffectView extends Viewport {

    private Image itemImage;
    private boolean draw = true;

    private TakeableItem takeableItem;

    public TransactionAreaEffectView(TransactionAreaEffect transactionAreaEffect){
        transactionAreaEffect.attach(this);
        this.takeableItem = transactionAreaEffect.getShopItem();

        itemImage = SpriteParser.getSpriteParser().getItemFromName(takeableItem.getName());
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        if(draw) {
            graphics2D.drawImage(itemImage, x * AreaSizes.TERRAIN_WIDTH, y * AreaSizes.TERRAIN_HEIGHT,
                    AreaSizes.AREA_EFFECT_WIDTH, AreaSizes.AREA_EFFECT_HEIGHT, this);
            graphics2D.drawString(Integer.toString(takeableItem.getValue()), x * AreaSizes.TERRAIN_WIDTH, y * AreaSizes.TERRAIN_HEIGHT);
        }
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    public void update(){
        draw = false;
    }
}
