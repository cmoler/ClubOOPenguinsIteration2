package Configs;

import java.awt.*;

public interface ImagesInfo {


    public static final String TILE_SHEET = "resources/images/terrain.png";
    public static final String AVATAR_SHEET = "resources/images/bluePenguin.png";
    public static final String AVATAR_SHEET_NINJA = "resources/images/Penguin_Chat_3_Ninja_spritesheet.png";

    public static final String AREAEFFECT_DAMAGE_FILELOCATION = "resources/images/Red_AOE.png";
    public static final String AREAEFFECT_HEAL_FILELOCATION = "resources/images/Green_AOE.png";
    public static final String AREAEFFECT_KILL_FILELOCATION = "resources/images/Black_AOE.png";
    public static final String AREAEFFECT_LEVELUP_FILELOCATION = "resources/images/Blue_AOE.png";
    public static final String ITEM_ITERACTIVE_FILELOCATION = "resources/images/Moss_Key_Pin.png";
    public static final String ITEM_ONESHOT_FILELOCATION = "resources/images/Fishburger.png";
    public static final String ITEM_TAKEABLE_FILELOCATION= "resources/images/Blue_Bunny_Ears.png";
    public static final String ITEM_TELEPORTER_FILELOCATION = "resources/images/Portal_box.png";
    public static final String RUNESCAPE_GUI_FILELOCATION = "resources/images/Interface.png";

    public static final String RED_CROSS_FILELOCATION = "resources/images/RedCross.png";
    public static final String SKULL_CROSS_BONES_FILELOCATION = "resources/images/SkullCrossBone.png";
    public static final String GOLD_STAR_FILELOCATION = "resources/images/goldstar.png";

    public static final String GLACIER_FILELOCATION = "resources/images/glacier.png";

    public static final Image RUNESCAPE_GUI = SpriteParser.getSpriteParser().getRunescapeGUI();

    public static final Image ICE_IMAGE = SpriteParser.getSpriteParser().getIceImage();
    public static final Image WATER_IMAGE = SpriteParser.getSpriteParser().getWaterImage();
    public static final Image GLACIER_IMAGE = SpriteParser.getSpriteParser().getGlacierImage();
    public static final Image OBSTACLE_IMAGE = SpriteParser.getSpriteParser().getObstacleItemImage();

    public static final Image AREAEFFECT_DAMAGE_IMAGE = SpriteParser.getSpriteParser().getAreaEffectDamageImage();
    public static final Image AREAEFFECT_HEAL_IMAGE = SpriteParser.getSpriteParser().getAreaEffectHealImage();
    public static final Image AREAEFFECT_KILL_IMAGE = SpriteParser.getSpriteParser().getAreaEffectKillImage();
    public static final Image AREAEFFECT_LEVELUP_IMAGE = SpriteParser.getSpriteParser().getAreaEffectLevelUpImage();

    public static final Image ITEM_ITERACTIVE_IMAGE = SpriteParser.getSpriteParser().getInteractiveItemImage();
    public static final Image ITEM_ONESHOT_IMAGE = SpriteParser.getSpriteParser().getOneShotItemImage();
    public static final Image ITEM_TAKEABLE_IMAGE = SpriteParser.getSpriteParser().getTakeAbleItemImage();
    public static final Image ITEM_TELEPORTER_IMAGE = SpriteParser.getSpriteParser().getTeleporterItemImage();

    public static final Image AVATAR_IMAGE = SpriteParser.getSpriteParser().getAvatarImage();
    public static final Image AVATAR_IMAGE_NINJA1 = SpriteParser.getSpriteParser().getAvatarImage_NINJA();


    public static final Image RED_CROSS_IMAGE = SpriteParser.getSpriteParser().getRedCrossImage();
    public static final Image SKULL_CROSS_BONES_IMAGE = SpriteParser.getSpriteParser().getSkullAndCrossBonesImage();
    public static final Image GOLD_STAR_IMAGE = SpriteParser.getSpriteParser().getGoldStarImage();

}
