package Model.Item.TakeableItem;

import Model.Item.TakeableItem.Armor.*;
import Model.Item.TakeableItem.BaneItem.*;
import Model.Item.TakeableItem.BoonItem.*;
import Model.Item.TakeableItem.BrawlingItem.*;
import Model.Item.TakeableItem.EnchantmentItem.*;
import Model.Item.TakeableItem.Key.*;
import Model.Item.TakeableItem.OneHandedWeaponItem.*;
import Model.Item.TakeableItem.Potion.*;
import Model.Item.TakeableItem.RangedWeaponItem.*;
import Model.Item.TakeableItem.StaffItem.*;
import Model.Item.TakeableItem.TwoHandedWeaponItem.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TakeableItemGenerator {

    private static TakeableItemGenerator takeableItemGenerator = null;
    private List<Integer> items ;

    protected TakeableItemGenerator(){
        //Singleton
        items = new ArrayList<>();
        for (int i = 0; i <= 29; i++){
            items.add(i);
        }
    }

    public static TakeableItemGenerator getTakeableItemGenerator(){
        if (takeableItemGenerator == null){
            takeableItemGenerator = new TakeableItemGenerator();
        }
        return takeableItemGenerator;
    }

    public TakeableItem getRandomItem(){
        Random random = new Random();
        int randomItem = random.nextInt(items.size());
        switch(randomItem){
            case 0:
                return new Body();
            case 1:
                return new Helmet();
            case 2:
                return new Leg();
            case 3:
                return new Ring();
            case 4:
                return new AngularIceAttack();
            case 5:
                return new LinearIceAttack();
            case 6:
                return new RadialIceBomb();
            case 7:
                return new Heal();
            case 8:
                return new IncreaseMaxHealth();
            case 9:
                return new IncreaseXP();
            case 10:
                return new BrassKnuckles();
            case 11:
                return new SpikedGloves();
            case 12:
                return new SwordHands();
            case 13:
                return new Charm();
            case 14:
                return new Insomnia();
            case 15:
                return new Seppuku();
            case 16:
                return new Key();
            case 17:
                return new BlueLightsaber();
            case 18:
                return new Mjolnir();
            case 19:
                return new ThunderBlade();
            case 20:
                return new Pizza();
            case 21:
                return new SnowLauncher();
            case 22:
                return new SnowShuriken();
            case 23:
                return new Staff();
            case 24:
                return new InquisitorLightsaber();
            case 25:
                return new JeweledCutlass();
            case 26:
                return new WaterHammer();
            case 27:
                return new HealthPotion();
            case 28:
                return new ManaPotion();
            case 29:
                return new XPPotion();
            default:
                return new Key();
        }
    }


}
