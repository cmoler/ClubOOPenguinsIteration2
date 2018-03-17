package View.AreaView;


import Configs.AreaSizes;
import Configs.Commons;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Entity.Entity;
import Model.Map.Direction;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class AvatarView extends Viewport {

    private Image avatarImage;
    private Entity entity;
    private int x;
    private int y;
    private Direction directionFacing;

    public AvatarView(Entity entity, int x, int y){
        this.entity = entity;
        entity.attach(this);
        directionFacing = entity.getDirectionFacing();
        avatarImage = SpriteParser.getSpriteParser().getAvatarImage(directionFacing);
        this.x = x;
        this.y = y;

        calculatePlayerOffset();
    }

    public Entity getEntity() {
        return entity;
    }

    private void calculatePlayerOffset(){
        double DESIRED_PLAYER_X_RATIO = 265.0f/768.0f;
        double DESIRED_PLAYER_Y_RATIO = 165.0f/501.0f;

        double DESIRED_PLAYER_X = DESIRED_PLAYER_X_RATIO * Commons.SCREEN_WIDTH;
        double DESIRED_PLAYER_Y = DESIRED_PLAYER_Y_RATIO * Commons.SCREEN_HEIGHT;

        int DESIRED_PLAYER_X_TILE = (int)(DESIRED_PLAYER_X / AreaSizes.TERRAIN_WIDTH);
        int DESIRED_PLAYER_Y_TILE = (int)(DESIRED_PLAYER_Y / AreaSizes.TERRAIN_HEIGHT);

        this.x = DESIRED_PLAYER_X_TILE;
        this.y = DESIRED_PLAYER_Y_TILE;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int renderX = (x) * AreaSizes.TERRAIN_WIDTH + AreaSizes.TERRAIN_WIDTH/4;
        int renderY = (y) * AreaSizes.TERRAIN_HEIGHT + AreaSizes.TERRAIN_HEIGHT/4;
       graphics2D.drawImage(avatarImage, renderX, renderY,
               AreaSizes.AVATAR_WIDTH, AreaSizes.AVATAR_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    public void setAvatarImage(Image e){
        this.avatarImage = e;
    }

    @Override
    public void update(){
        directionFacing = entity.getDirectionFacing();
        avatarImage = SpriteParser.getSpriteParser().getAvatarImage(directionFacing);
    }
}
