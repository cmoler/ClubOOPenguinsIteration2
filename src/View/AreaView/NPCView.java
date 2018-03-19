package View.AreaView;

import Configs.AreaSizes;
import Configs.Commons;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Entity.Entity;
import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Map.Direction;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class NPCView extends Viewport {

    private Image avatarImage;
    private NPC npc;
    private Player player;
    private int x;
    private int y;
    private String role;
    private Direction directionFacing;

    public NPCView(NPC npc){
        this.npc = npc;
        switch (npc.getColor()){
            case "blue":
                avatarImage = ImagesInfo.NPC_BLUE_IMAGE;
                break;
            case "red":
                avatarImage = ImagesInfo.NPC_RED_IMAGE;
                break;
            case "yellow":
                avatarImage = ImagesInfo.NPC_YELLOW_IMAGE;
                break;
        }
        npc.attach(this);
        this.player = npc.getPlayer();
        if(player!= null) update();
        directionFacing = npc.getDirectionFacing();
    }

    @Override
    public int getLocationX(){
        return npc.getLocation().getxCoordinate();
    }

    @Override
    public int getLocationY(){
        return npc.getLocation().getyCoordinate();
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        if(!npc.isDone()) {
            graphics2D.drawImage(avatarImage, x * AreaSizes.TERRAIN_WIDTH + AreaSizes.TERRAIN_WIDTH / 4, y * AreaSizes.TERRAIN_HEIGHT + AreaSizes.TERRAIN_HEIGHT / 4,
                    AreaSizes.AVATAR_WIDTH, AreaSizes.AVATAR_HEIGHT, this);
        }

        if(npc.isTalking())
            graphics2D.drawString("" + npc.getTalkString(), x * AreaSizes.TERRAIN_WIDTH + AreaSizes.TERRAIN_WIDTH/4,
                    y * AreaSizes.TERRAIN_HEIGHT + AreaSizes.TERRAIN_HEIGHT / 4);


    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }


    @Override
    public void update(){

    }
}
