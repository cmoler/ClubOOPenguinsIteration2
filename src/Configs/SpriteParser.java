package Configs;

import Model.Map.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteParser {

    public static SpriteParser spriteParser = null;

    private SpriteParser(){
        getSprites();
    }

    public static SpriteParser getSpriteParser(){
        if(spriteParser == null){
            spriteParser = new SpriteParser();
        }
        return spriteParser;
    }

    private BufferedImage[] tileSprites;

    private BufferedImage[] avatarSprites;
    private BufferedImage[] avatarSprites_NINJA;

    public Image getItemFromName(String name){
        ImageIcon imageIcon = new ImageIcon("resources/images/equipment/"+name+".png");
        return imageIcon.getImage();
    }

    public Image getIceImage(){
        return tileSprites[11*12];
    }

    public Image getWaterImage(){
        return tileSprites[5*12];
    }

    public Image getGlacierImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.GLACIER_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAreaEffectDamageImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.AREAEFFECT_DAMAGE_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAreaEffectHealImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.AREAEFFECT_HEAL_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAreaEffectKillImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.AREAEFFECT_KILL_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAreaEffectLevelUpImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.AREAEFFECT_LEVELUP_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getInteractiveItemImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.ITEM_ITERACTIVE_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getOneShotItemImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.ITEM_ONESHOT_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getTakeAbleItemImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.ITEM_TAKEABLE_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getTeleporterItemImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.ITEM_TELEPORTER_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getObstacleItemImage(){
        return tileSprites[7*12];
    }

    public Image getRunescapeGUI(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.RUNESCAPE_GUI_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getRedCrossImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.RED_CROSS_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getSkullAndCrossBonesImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.SKULL_CROSS_BONES_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getGoldStarImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.GOLD_STAR_FILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAvatarImage(Direction direction){
        switch (direction){
            case N:
                return avatarSprites[4];
            case NE:
                return avatarSprites[5];
            case E:
                return avatarSprites[6];
            case SE:
                return avatarSprites[7];
            case S:
                return avatarSprites[0];
            case SW:
                return avatarSprites[1];
            case W:
                return avatarSprites[2];
            case NW:
                return avatarSprites[3];
        }
        return null;
    }

    public Image getAvatarImage_NINJA() {return avatarSprites_NINJA[0];}

    private void getSprites(){
        getTileSprites();
        getAvatarSprites();
        getAvatarSprites_NINJA();
    }

    private void getTileSprites(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.TILE_SHEET));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 32;
        final int height = 32;
        final int rows = 18;
        final int cols = 12;
        tileSprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                tileSprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }

    private void getAvatarSprites(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.AVATAR_SHEET));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 37;
        final int height = 40;
        final int rows = 1;
        final int cols = 7;
        avatarSprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                avatarSprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }
    private void getAvatarSprites_NINJA(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.AVATAR_SHEET_NINJA));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 40;
        final int height = 40;
        final int rows = 1;
        final int cols = 1;
        avatarSprites_NINJA = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                avatarSprites_NINJA[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }

}
