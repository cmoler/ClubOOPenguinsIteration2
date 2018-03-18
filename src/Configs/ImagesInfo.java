package Configs;

import java.awt.*;

public interface ImagesInfo {


    public static final String TILE_SHEET = "resources/images/terrain/terrain.png";
    public static final String AVATAR_SHEET_BLUE = "resources/images/playerSprites/bluePenguin.png";
    public static final String AVATAR_SHEET_RED = "resources/images/playerSprites/redPenguin.png";
    public static final String AVATAR_SHEET_NINJA = "resources/images/playerSprites/Penguin_Chat_3_Ninja_spritesheet.png";

    public static final String NPC_BLUE_FILELOCATION = "resources/images/clubPenguinEnemies/blackPuffle.png";
    public static final String NPC_RED_FILELOCATION = "resources/images/clubPenguinEnemies/greenPuffle.png";
    public static final String NPC_YELLOW_FILELOCATION = "resources/images/clubPenguinEnemies/chickenPuffle.png";

    public static final String AREAEFFECT_DAMAGE_FILELOCATION = "resources/images/aoe/Red_AOE.png";
    public static final String AREAEFFECT_HEAL_FILELOCATION = "resources/images/aoe/Green_AOE.png";
    public static final String AREAEFFECT_KILL_FILELOCATION = "resources/images/aoe/Black_AOE.png";
    public static final String AREAEFFECT_LEVELUP_FILELOCATION = "resources/images/aoe/Blue_AOE.png";
    public static final String KEY_FILELOCATION = "resources/images/clubPenguinItems/Moss_Key_Pin.png";
    public static final String BURGER_FILELOCATION = "resources/images/clubPenguinItems/Fishburger.png";
    public static final String ITEM_TAKEABLE_FILELOCATION= "resources/images/clubPenguinItems/Blue_Bunny_Ears.png";
    public static final String PORTAL_FILELOCATION = "resources/images/clubPenguinItems/Portal_box.png";
    public static final String RUNESCAPE_GUI_FILELOCATION = "resources/images/Interface.png";
    public static final String PROJECTILE_LINEARICEATTACKFILELOCATION = "resources/images/magic/linearIceAttack.png";
    public static final String PROJECTILE_ANGULARICEATTACKFILELOCATION = "resources/images/magic/anglularIceAttack.png";
    public static final String PROJECTILE_RADIALICEATTACKFILELOCATION = "resources/images/magic/radialIceAttack.png";
    public static final String PROJECTILE_PIZZAFILELOCATION = "resources/images/equipment/pizza.png";

    public static final String EXP_POTION_FILELOCATION = "resources/images/equipment/expPotion.png";
    public static final String HEALTH_POTION_FILELOCATION = "resources/images/equipment/healthPotion.png";
    public static final String MANA_POTION_FILELOCATION = "resources/images/equipment/manaPotion.png";

    public static final String RED_CROSS_FILELOCATION = "resources/images/terrain/RedCross.png";
    public static final String SKULL_CROSS_BONES_FILELOCATION = "resources/images/terrain/SkullCrossBone.png";
    public static final String GOLD_STAR_FILELOCATION = "resources/images/terrain/goldstar.png";

    public static final String TRAP_FILELOCATION = "resources/images/Beartrap.png";
    public static final String CHEST_FILELOCATION = "resources/images/chest.png";

    public static final String GLACIER_FILELOCATION = "resources/images/terrain/glacier.png";

    public static final String CHARM_FILELOCATION = "resources/images/magic/charm.png";
    public static final String HEAL_FILELOCATION = "resources/images/magic/heal.png";
    public static final String INCREASE_LEVEL_FILELOCATION = "resources/images/magic/increaseLevel.png";
    public static final String INCREASE_MAX_HEALTH_FILELOCATION = "resources/images/magic/increaseMaxHealth.png";
    public static final String SEPPUKU_FILELOCATION = "resources/images/magic/seppuku.png";
    public static final String SLEEP_FILELOCATION = "resources/images/magic/sleep.png";

    public static final String INCREASE_SKILL_ICON = "resources/images/IncreaseSkillIcon.png";

    public static final Image RUNESCAPE_GUI = SpriteParser.getSpriteParser().getRunescapeGUI();

    public static final Image NPC_BLUE_IMAGE = SpriteParser.getSpriteParser().getNPCBlueImage();
    public static final Image NPC_RED_IMAGE = SpriteParser.getSpriteParser().getNPCRedImage();
    public static final Image NPC_YELLOW_IMAGE = SpriteParser.getSpriteParser().getNPCYellowImage();

    public static final Image TRAP_IMAGE = SpriteParser.getSpriteParser().getTrapImage();
    public static final Image CHEST_OPEN_IMAGE = SpriteParser.getSpriteParser().getOpenChestImage();
    public static final Image CHEST_CLOSED_IMAGE = SpriteParser.getSpriteParser().getClosedChestImage();

    public static final Image ICE_IMAGE = SpriteParser.getSpriteParser().getIceImage();
    public static final Image WATER_IMAGE = SpriteParser.getSpriteParser().getWaterImage();
    public static final Image GLACIER_IMAGE = SpriteParser.getSpriteParser().getGlacierImage();
    public static final Image OBSTACLE_IMAGE = SpriteParser.getSpriteParser().getObstacleItemImage();

    public static final Image AREAEFFECT_DAMAGE_IMAGE = SpriteParser.getSpriteParser().getAreaEffectDamageImage();
    public static final Image AREAEFFECT_HEAL_IMAGE = SpriteParser.getSpriteParser().getAreaEffectHealImage();
    public static final Image AREAEFFECT_KILL_IMAGE = SpriteParser.getSpriteParser().getAreaEffectKillImage();
    public static final Image AREAEFFECT_LEVELUP_IMAGE = SpriteParser.getSpriteParser().getAreaEffectLevelUpImage();

    public static final Image KEY_IMAGE = SpriteParser.getSpriteParser().getInteractiveItemImage();
    public static final Image BURGER_IMAGE = SpriteParser.getSpriteParser().getOneShotItemImage();
    public static final Image ITEM_TAKEABLE_IMAGE = SpriteParser.getSpriteParser().getTakeAbleItemImage();
    public static final Image ITEM_TELEPORTER_IMAGE = SpriteParser.getSpriteParser().getTeleporterItemImage();

    public static final Image PROJECTILE_LINEARICEATTACK = SpriteParser.getSpriteParser().getLinearIceAttackImage();
    public static final Image PROJECTILE_ANGULARICEATTACK = SpriteParser.getSpriteParser().getAngularIceAttackImage();
    public static final Image PROJECTILE_RADIALICEATTACK = SpriteParser.getSpriteParser().getRadialIceAttackImage();
    public static final Image PROJECTILE_PIZZA = SpriteParser.getSpriteParser().getPizzaImage();

    public static final Image EXP_POTION_IMAGE = SpriteParser.getSpriteParser().getExpPotionImage();
    public static final Image HEALTH_POTION_IMAGE = SpriteParser.getSpriteParser().getHealthPotionImage();
    public static final Image MANA_POTION_IMAGE = SpriteParser.getSpriteParser().getManaPotionImage();

    public static final Image RED_CROSS_IMAGE = SpriteParser.getSpriteParser().getRedCrossImage();
    public static final Image SKULL_CROSS_BONES_IMAGE = SpriteParser.getSpriteParser().getSkullAndCrossBonesImage();
    public static final Image GOLD_STAR_IMAGE = SpriteParser.getSpriteParser().getGoldStarImage();

    public static final Image CHARM_IMAGE = SpriteParser.getSpriteParser().getCharmImage();
    public static final Image HEAL_IMAGE = SpriteParser.getSpriteParser().getHealImage();
    public static final Image INCREASE_LEVEL_IMAGE = SpriteParser.getSpriteParser().getIncreaseLevelImage();
    public static final Image INCREASE_MAX_HEALTH_IMAGE = SpriteParser.getSpriteParser().getIncreaseMaxHealthImage();
    public static final Image SEPPUKU_IMAGE = SpriteParser.getSpriteParser().getSeppukuImage();
    public static final Image SLEEP_IMAGE = SpriteParser.getSpriteParser().getSleepImage();

}
