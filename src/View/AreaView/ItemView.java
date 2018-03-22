package View.AreaView;


import Configs.AreaSizes;
import Configs.ImagesInfo;
import Configs.SpriteParser;
import Model.Item.InteractiveItem.ChestInteractiveItem;
import Model.Item.Item;
import Model.Item.TakeableItem.Armor.Body;
import Model.Item.TakeableItem.Armor.Helmet;
import Model.Item.TakeableItem.Armor.Leg;
import Model.Item.TakeableItem.Armor.Ring;
import Model.Item.TakeableItem.BaneItem.AngularIceAttack;
import Model.Item.TakeableItem.BaneItem.LinearIceAttack;
import Model.Item.TakeableItem.BaneItem.RadialIceBomb;
import Model.Item.TakeableItem.BoonItem.Heal;
import Model.Item.TakeableItem.BoonItem.IncreaseMaxHealth;
import Model.Item.TakeableItem.BoonItem.IncreaseXP;
import Model.Item.TakeableItem.BrawlingItem.BrassKnuckles;
import Model.Item.TakeableItem.BrawlingItem.SpikedGloves;
import Model.Item.TakeableItem.BrawlingItem.SwordHands;
import Model.Item.TakeableItem.EnchantmentItem.Charm;
import Model.Item.TakeableItem.EnchantmentItem.Insomnia;
import Model.Item.TakeableItem.EnchantmentItem.Seppuku;
import Model.Item.TakeableItem.Key.Key;
import Model.Item.TakeableItem.OneHandedWeaponItem.BlueLightsaber;
import Model.Item.TakeableItem.OneHandedWeaponItem.Mjolnir;
import Model.Item.TakeableItem.OneHandedWeaponItem.ThunderBlade;
import Model.Item.TakeableItem.Potion.HealthPotion;
import Model.Item.TakeableItem.Potion.ManaPotion;
import Model.Item.TakeableItem.Potion.XPPotion;
import Model.Item.TakeableItem.RangedWeaponItem.Pizza;
import Model.Item.TakeableItem.RangedWeaponItem.SnowLauncher;
import Model.Item.TakeableItem.RangedWeaponItem.SnowShuriken;
import Model.Item.TakeableItem.StaffItem.StaffItem;
import Model.Item.TakeableItem.TwoHandedWeaponItem.InquisitorLightsaber;
import Model.Item.TakeableItem.TwoHandedWeaponItem.JeweledCutlass;
import Model.Item.TakeableItem.TwoHandedWeaponItem.WaterHammer;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class ItemView extends Viewport {

    private Image itemImage;

    public ItemView(String item){
        this.itemImage = parseItem(item);
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(itemImage, (x* AreaSizes.TERRAIN_WIDTH)+(AreaSizes.TERRAIN_WIDTH/4), (y* AreaSizes.TERRAIN_HEIGHT)+(AreaSizes.TERRAIN_HEIGHT/4),
                AreaSizes.ITEM_WIDTH, AreaSizes.ITEM_HEIGHT,this );
    }

    private Image parseItem(String itemName){
        switch(itemName){
            case "body":
                return SpriteParser.getSpriteParser().getItemFromName("chest");
            case "head":
                return SpriteParser.getSpriteParser().getItemFromName("helmet");
            case "legs":
                return SpriteParser.getSpriteParser().getItemFromName("legs");
            case "ring":
                return SpriteParser.getSpriteParser().getItemFromName("ring");
            case "angularIceAttack":
                return ImagesInfo.PROJECTILE_ANGULARICEATTACK;
            case "linearIceAttack":
                return ImagesInfo.PROJECTILE_LINEARICEATTACK;
            case "radialIceAttack":
                return ImagesInfo.PROJECTILE_LINEARICEATTACK;
            case "heal":
                return ImagesInfo.HEAL_IMAGE;
            case "increaseMaxHealth":
                return ImagesInfo.INCREASE_MAX_HEALTH_IMAGE;
            case "increaseLevel":
                return ImagesInfo.INCREASE_LEVEL_IMAGE;
            case "brassKnuckles":
                return SpriteParser.getSpriteParser().getItemFromName("brassKnuckles");
            case "spikedGloves":
                return SpriteParser.getSpriteParser().getItemFromName("spikedGloves");
            case "swordHands":
                return SpriteParser.getSpriteParser().getItemFromName("swordHands");
            case "charm":
                return ImagesInfo.CHARM_IMAGE;
            case "sleep":
                return ImagesInfo.SLEEP_IMAGE;
            case "seppuku":
                return ImagesInfo.SEPPUKU_IMAGE;
            case "key":
                return ImagesInfo.KEY_IMAGE;
            case "blueLightsaber":
                return SpriteParser.getSpriteParser().getItemFromName("blueLightsaber");
            case "mjolnir":
                return SpriteParser.getSpriteParser().getItemFromName("mjolnir");
            case "thunderBlade":
                return SpriteParser.getSpriteParser().getItemFromName("thunderBlade");
            case "pizza":
                return SpriteParser.getSpriteParser().getItemFromName("pizza");
            case "snowLauncher":
                return SpriteParser.getSpriteParser().getItemFromName("snowLauncher");
            case "snowShuriken":
                return SpriteParser.getSpriteParser().getItemFromName("snowShuriken");
            case "staffItem":
                return SpriteParser.getSpriteParser().getItemFromName("staff");
            case "inquisitorLightsaber":
                return SpriteParser.getSpriteParser().getItemFromName("inquisitorLightsaber");
            case "jeweledCutlass":
                return SpriteParser.getSpriteParser().getItemFromName("jeweledCutlass");
            case "waterHammer":
                return SpriteParser.getSpriteParser().getItemFromName("waterHammer");
            case "healthPotion":
                return ImagesInfo.HEALTH_POTION_IMAGE;
            case "manaPotion":
                return ImagesInfo.MANA_POTION_IMAGE;
            case "xpPotion":
                return ImagesInfo.EXP_POTION_IMAGE;
            case "chestInteractiveItemOpen":
                return ImagesInfo.CHEST_OPEN_IMAGE;
            case "chestInteractiveItemClosed":
                return ImagesInfo.CHEST_CLOSED_IMAGE;
            default:
                return SpriteParser.getSpriteParser().getItemFromName("chest");
        }
    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
