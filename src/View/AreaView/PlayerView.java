package View.AreaView;


import Configs.AreaSizes;
import Configs.Commons;
import Configs.SpriteParser;
import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Map.Direction;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class PlayerView extends Viewport {

    private Image avatarImage;
    private Player player;
    private String role;
    private Direction directionFacing;

    public PlayerView(Player player){
        this.player = player;
        this.role = player.getRole().getRoleType().name();
        player.attach(this);
        directionFacing = player.getDirectionFacing();
        avatarImage = SpriteParser.getSpriteParser().getAvatarImage_BLUE(directionFacing);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        double DESIRED_PLAYER_X_RATIO = 265.0f/768.0f;
        double DESIRED_PLAYER_Y_RATIO = 165.0f/501.0f;

        int DESIRED_PLAYER_X = (int)DESIRED_PLAYER_X_RATIO * Commons.SCREEN_WIDTH;
        int DESIRED_PLAYER_Y = (int)DESIRED_PLAYER_Y_RATIO * Commons.SCREEN_HEIGHT;

        graphics2D.drawImage(avatarImage, DESIRED_PLAYER_X, DESIRED_PLAYER_Y,
               AreaSizes.AVATAR_WIDTH, AreaSizes.AVATAR_HEIGHT,this );
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }


    @Override
    public void update(){
        directionFacing = player.getDirectionFacing();
        switch (role){
            case "Smasher":
                avatarImage = SpriteParser.getSpriteParser().getAvatarImage_RED(directionFacing);
                break;
            case "Sneak":
                avatarImage = SpriteParser.getSpriteParser().getAvatarImage_NINJA(directionFacing);
                break;
            case "Summoner":
                avatarImage = SpriteParser.getSpriteParser().getAvatarImage_BLUE(directionFacing);
                break;
        }
    }
}
