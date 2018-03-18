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

    private BufferedImage[] avatarSprites_BLUE;
    private BufferedImage[] avatarSprites_RED;
    private BufferedImage[] avatarSprites_NINJA;
    private BufferedImage[] chestSprites;

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

    public Image getLinearIceAttackImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.PROJECTILE_LINEARICEATTACKFILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAngularIceAttackImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.PROJECTILE_ANGULARICEATTACKFILELOCATION);
        return imageIcon.getImage();
    }

    public Image getRadialIceAttackImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.PROJECTILE_RADIALICEATTACKFILELOCATION);
        return imageIcon.getImage();
    }

    public Image getPizzaImage(){
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.PROJECTILE_PIZZAFILELOCATION);
        return imageIcon.getImage();
    }

    public Image getAvatarImage_BLUE(Direction direction){
        switch (direction){
            case N:
                return avatarSprites_BLUE[4];
            case NE:
                return avatarSprites_BLUE[5];
            case E:
                return avatarSprites_BLUE[6];
            case SE:
                return avatarSprites_BLUE[7];
            case S:
                return avatarSprites_BLUE[0];
            case SW:
                return avatarSprites_BLUE[1];
            case W:
                return avatarSprites_BLUE[2];
            case NW:
                return avatarSprites_BLUE[3];
        }
        return null;
    }

    public Image getAvatarImage_RED(Direction direction){
        switch (direction){
            case N:
                return avatarSprites_RED[4];
            case NE:
                return avatarSprites_RED[5];
            case E:
                return avatarSprites_RED[6];
            case SE:
                return avatarSprites_RED[7];
            case S:
                return avatarSprites_RED[0];
            case SW:
                return avatarSprites_RED[1];
            case W:
                return avatarSprites_RED[2];
            case NW:
                return avatarSprites_RED[3];
        }
        return null;
    }

    public Image getAvatarImage_NINJA(Direction direction) {
        switch (direction){
            case N:
                return avatarSprites_NINJA[3];
            case NE:
                return avatarSprites_NINJA[7];
            case E:
                return avatarSprites_NINJA[6];
            case SE:
                return avatarSprites_NINJA[5];
            case S:
                return avatarSprites_NINJA[4];
            case SW:
                return avatarSprites_NINJA[0];
            case W:
                return avatarSprites_NINJA[1];
            case NW:
                return avatarSprites_NINJA[2];
        }
        return null;
    }

    private void getSprites(){
        getTileSprites();
        getAvatarSprites_BLUE();
        getAvatarSprites_RED();
        getAvatarSprites_NINJA();
        getChestSprites();
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

    private void getAvatarSprites_BLUE(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.AVATAR_SHEET_BLUE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 37;
        final int height = 40;
        final int rows = 1;
        final int cols = 7;
        avatarSprites_BLUE = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                avatarSprites_BLUE[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }

    private void getAvatarSprites_RED(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.AVATAR_SHEET_RED));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 37;
        final int height = 40;
        final int rows = 1;
        final int cols = 7;
        avatarSprites_RED = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                avatarSprites_RED[(i * cols) + j] = bigImg.getSubimage(
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

        final int width = 36;
        final int height = 40;
        final int rows = 2;
        final int cols = 4;
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

    private void getChestSprites(){
        BufferedImage bigImg = null;
        try {
            bigImg = ImageIO.read(new File(ImagesInfo.CHEST_FILELOCAITON));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = 100;
        final int height = 139;
        final int rows = 1;
        final int cols = 3;
        chestSprites = new BufferedImage[rows * cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                chestSprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }

    public Image getNPCBlueImage() {
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.NPC_BLUE_IMAGE);
        return imageIcon.getImage();
    }

    public Image getNPCRedImage() {
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.NPC_RED_IMAGE);
        return imageIcon.getImage();
    }

    public Image getNPCYellowImage() {
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.NPC_YELLOW_IMAGE);
        return imageIcon.getImage();
    }

    public Image getClosedChestImage() {
        return chestSprites[0];
    }

    public Image getOpenChestImage() {
        return chestSprites[2];
    }

    public Image getTrapImage() {
        ImageIcon imageIcon = new ImageIcon(ImagesInfo.TRAP_FILELOCATION);
        return imageIcon.getImage();
    }
}
