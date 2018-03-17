package Configs;

import java.awt.*;

public interface ImagesInfo {


    public static final String TILE_SHEET = "resources/images/terrain/terrain.png";
    public static final String AVATAR_SHEET_BLUE = "resources/images/playerSprites/bluePenguin.png";
    public static final String AVATAR_SHEET_RED = "resources/images/playerSprites/redPenguin.png";
    public static final String AVATAR_SHEET_NINJA = "resources/images/playerSprites/Penguin_Chat_3_Ninja_spritesheet.png";

    public static final String AREAEFFECT_DAMAGE_FILELOCATION = "resources/images/aoe/Red_AOE.png";
    public static final String AREAEFFECT_HEAL_FILELOCATION = "resources/images/aoe/Green_AOE.png";
    public static final String AREAEFFECT_KILL_FILELOCATION = "resources/images/aoe/Black_AOE.png";
    public static final String AREAEFFECT_LEVELUP_FILELOCATION = "resources/images/aoe/Blue_AOE.png";
    public static final String ITEM_ITERACTIVE_FILELOCATION = "resources/images/clubPenguinItems/Moss_Key_Pin.png";
    public static final String ITEM_ONESHOT_FILELOCATION = "resources/images/clubPenguinItems/Fishburger.png";
    public static final String ITEM_TAKEABLE_FILELOCATION= "resources/images/clubPenguinItems/Blue_Bunny_Ears.png";
    public static final String ITEM_TELEPORTER_FILELOCATION = "resources/images/clubPenguinItems/Portal_box.png";
    public static final String RUNESCAPE_GUI_FILELOCATION = "resources/images/Interface.png";
    public static final String PROJECTILE_LINEARICEATTACKFILELOCATION = "resources/images/magic/linearIceAttack.png";
    public static final String PROJECTILE_ANGULARICEATTACKFILELOCATION = "resources/images/magic/anglularIceAttack.png";
    public static final String PROJECTILE_RADIALICEATTACKFILELOCATION = "resources/images/magic/radialIceAttack.png";
    public static final String PROJECTILE_PIZZAFILELOCATION = "resources/images/equipment/pizza.png";

    public static final String RED_CROSS_FILELOCATION = "resources/images/terrain/RedCross.png";
    public static final String SKULL_CROSS_BONES_FILELOCATION = "resources/images/terrain/SkullCrossBone.png";
    public static final String GOLD_STAR_FILELOCATION = "resources/images/terrain/goldstar.png";

    public static final String GLACIER_FILELOCATION = "resources/images/terrain/glacier.png";

    public static final String INCREASE_SKILL_ICON = "resources/images/IncreaseSkillIcon.png";

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

    public static final Image PROJECTILE_LINEARICEATTACK = SpriteParser.getSpriteParser().getLinearIceAttackImage();
    public static final Image PROJECTILE_ANGULARICEATTACK = SpriteParser.getSpriteParser().getAngularIceAttackImage();
    public static final Image PROJECTILE_RADIALICEATTACK = SpriteParser.getSpriteParser().getRadialIceAttackImage();
    public static final Image PROJECTILE_PIZZA = SpriteParser.getSpriteParser().getPizzaImage();

    public static final Image RED_CROSS_IMAGE = SpriteParser.getSpriteParser().getRedCrossImage();
    public static final Image SKULL_CROSS_BONES_IMAGE = SpriteParser.getSpriteParser().getSkullAndCrossBonesImage();
    public static final Image GOLD_STAR_IMAGE = SpriteParser.getSpriteParser().getGoldStarImage();

}
