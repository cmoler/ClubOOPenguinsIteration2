package View.AreaView;

import Configs.AreaSizes;
import Configs.Commons;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Entity.Entity;
import Model.Entity.NPC.NPC;
import Model.Map.Direction;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class NPCView extends Viewport {

    private Image avatarImage;
    private NPC npc;
    private int x;
    private int y;
    private String role;
    private Direction directionFacing;

    public NPCView(NPC npc, int x, int y){
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
        directionFacing = npc.getDirectionFacing();
        this.x = x;
        this.y = y;
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


    @Override
    public void update(){
        directionFacing = npc.getDirectionFacing();
    }
}
