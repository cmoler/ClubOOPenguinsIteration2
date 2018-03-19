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
    public void draw(Graphics2D graphics2D) {
        if(0 < x && x < Commons.SCREEN_WIDTH &&
                0 < y && y < Commons.SCREEN_HEIGHT) {
            graphics2D.drawImage(avatarImage, x, y,
                    AreaSizes.AVATAR_WIDTH, AreaSizes.AVATAR_HEIGHT, this);
        }
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }


    @Override
    public void update(){
        if(player == null) this.player = npc.getPlayer();
        if (player != null){
            directionFacing = npc.getDirectionFacing();
            int playerX = player.getLocation().getxCoordinate();
            int playerY = player.getLocation().getyCoordinate();

            int NPCX = npc.getLocation().getxCoordinate();
            int NPCY = npc.getLocation().getyCoordinate();

            this.x = (NPCX - playerX) * AreaSizes.TERRAIN_WIDTH;
            this.y = (NPCY - playerY) * AreaSizes.TERRAIN_HEIGHT;
        }
    }
}
