package Controller.SavingLoading;

import Model.Entity.*;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.*;
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
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TwoHandedWeaponItem.InquisitorLightsaber;
import Model.Item.TakeableItem.TwoHandedWeaponItem.JeweledCutlass;
import Model.Item.TakeableItem.TwoHandedWeaponItem.WaterHammer;
import Model.Map.AreaEffect.*;
import Model.Map.Location;
import Model.Map.Terrain.Terrain;
import Model.Map.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    private JSONObject saveFileJSON;

    public Deserializer(JSONObject saveFileJSON){
        this.saveFileJSON = saveFileJSON;
    }

    public void deserializeWorld(){
        World world = World.getWorld();
    }

    public Player deserializePlayer(){

        JSONObject playerJSON = saveFileJSON.getJSONObject("Player");

        Inventory   inventory = deserializeInventory(playerJSON.getJSONObject("Inventory"));
        Equipment   equipment = deserializeEquipment(playerJSON.getJSONObject("Equipment"));
        Location     location = deserializeLocation(playerJSON.getJSONObject("Location"));
        Role             role = deserializeRole(playerJSON.getJSONObject("Role"));
        EntityType entityType = deserializeEntityType(playerJSON);


        int level = deserializeLevel(playerJSON);
        int HP    = deserializeHP(playerJSON);
        int maxHP = deserializeMaxHP(playerJSON);
        int mana  = deserializeMana(playerJSON);
        int XP    = deserializeXP(playerJSON);
        int gold  = deserializeGold(playerJSON);

        return new Player(role, entityType);
    }

    private EntityType deserializeEntityType(JSONObject playerJSON){
        if(playerJSON.getString("EntityType").equals("ICE")){
            return EntityType.ICE;
        }
        else{
            return EntityType.WATER;
        }
    }

    private Role deserializeRole(JSONObject playerRoleJSON){

        BindWounds bindWounds = new BindWounds(playerRoleJSON.getInt("BindWoundsLevel"));
        Bargain bargain = new Bargain(playerRoleJSON.getInt("BargainLevel"));
        Observation observation = new Observation(playerRoleJSON.getInt("ObservationLevel"));

        JSONObject roleJSON = playerRoleJSON.getJSONObject("Role");
        String roleType = roleJSON.getString("RoleName");

        if(roleType.equals("Smasher")){

            OneHandedWeapon oneHandedWeapon = new OneHandedWeapon(roleJSON.getInt("OneHandedWeaponLevel"));
            TwoHandedWeapon twoHandedWeapon = new TwoHandedWeapon(roleJSON.getInt("TwoHandedWeaponLevel"));
            Brawl brawl = new Brawl(roleJSON.getInt("BrawlLevel"));

            return new Smasher(bindWounds, bargain, observation, oneHandedWeapon, twoHandedWeapon, brawl);

        }

        else if(roleType.equals("Summoner")){

            Enchantment enchantment = new Enchantment(roleJSON.getInt("EnchantmentLevel"));
            Boon boon = new Boon(roleJSON.getInt("BoonLevel"));
            Bane bane = new Bane(roleJSON.getInt("BaneLevel"));
            Staff staff = new Staff(roleJSON.getInt("StaffLevel"));

            return new Summoner(bindWounds, bargain, observation, enchantment, boon, bane, staff);
        }

        else{

            PickPocket pickPocket = new PickPocket(roleJSON.getInt("PickPocketLevel"));
            DetectAndRemoveTrap detectAndRemoveTrap = new DetectAndRemoveTrap(roleJSON.getInt("DetectAndRemoveTrapLevel"));
            Creep creep = new Creep(roleJSON.getInt("CreepLevel"));
            RangedWeapon rangedWeapon = new RangedWeapon(roleJSON.getInt("RangedWeaponLevel"));

            return new Sneak(bindWounds, bargain, observation, pickPocket, detectAndRemoveTrap, creep, rangedWeapon);
        }

    }

    private Location deserializeLocation(JSONObject location){

        //DAMAGE, HEAL, KILL, LEVELUP, TELEPORT, TRAP, TRANSACTION

        AreaEffect areaEffect;
        Terrain terrain;

        JSONObject areaEffectJSON = location.getJSONObject("AreaEffect");
        String areaEffectType = areaEffectJSON.getString("Type");
        if(areaEffectType.equals("DAMAGE")){
            areaEffect = new DamageAreaEffect();
        }
        else if(areaEffectType.equals("HEAL")){
            areaEffect = new HealAreaEffect();
        }
        else if(areaEffectType.equals("KILL")){
            areaEffect = new KillAreaEffect();
        }
        else if(areaEffectType.equals("LEVELUP")){
            areaEffect = new LevelUpAreaEffect();
        }
        else if(areaEffectType.equals("TELEPORT")){
            String mapID = areaEffectJSON.getString("mapID");
            int X = areaEffectJSON.getInt("X");
            int Y = areaEffectJSON.getInt("Y");
            areaEffect = new TeleportAreaEffect(mapID,X,Y);
        }
        else if(areaEffectType.equals("TRAP")){
            areaEffect = new TrapAreaEffect();
        }
        else {
            areaEffect = new TransactionAreaEffect();
        }

        List<Item> items = new ArrayList<>();
        JSONArray jsonItems = location.getJSONArray("Items");
        for(int itemIndex = 0; itemIndex < jsonItems.length(); itemIndex++){
            String itemType = jsonItems.getString(itemIndex);
            TakeableItem takeableItem = parseItem(itemType);
            items.add(takeableItem);
        }

        
        /*
        private JSONObject saveLocation(Location location) {
        JSONObject locationJSON = new JSONObject();
        locationJSON.put("AreaEffect",  ""+location.getAreaEffect().getAreaEffectType() );
        locationJSON.put("Terrain", ""+location.getTerrain().getTerrainType());
        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 0; i < location.getItems().size(); i++){
//            itemList.add(location.getItems().get(i).getName());
        }
        locationJSON.put("Items", itemList);
        return locationJSON;
    }
         */


    }

    private Inventory deserializeInventory(JSONObject inventory){
        Inventory inventoryModel = new Inventory();
        JSONArray items = inventory.getJSONArray("Items");
        for(int i = 0; i < items.length(); ++i){
            Object item = items.get(i);
            TakeableItem takeableItem = parseItem(item.toString());
            inventoryModel.addItem(takeableItem);
        }

        return inventoryModel;
    }

    private Equipment deserializeEquipment(JSONObject equipment, Player player){
        Equipment newEquipment = new Equipment(player);

        JSONObject hotbar = equipment.getJSONObject("Hotbar");
        JSONArray hotbarItems = hotbar.getJSONArray("Items");
        for (int i = 0; i < hotbarItems.length(); i++) {
            TakeableItem item = parseItem(hotbarItems.getString(i));
            newEquipment.equip(item);
        }
        JSONObject head;
        JSONObject body;
        JSONObject legs;
        JSONObject ring;

        return newEquipment;
    }

    private TakeableItem parseItem(String itemName){
        switch(itemName){
            case "body":
                return new Body();
            case "helmet":
                return new Helmet();
            case "legs":
                return new Leg();
            case "ring":
                return new Ring();
            case "angularIceAttack":
                return new AngularIceAttack();
            case "linearIceAttack":
                return new LinearIceAttack();
            case "radialIceAttack":
                return new RadialIceBomb();
            case "heal":
                return new Heal();
            case "increaseMaxHealth":
                return new IncreaseMaxHealth();
            case "increaseLevel":
                return new IncreaseXP();
            case "brassKnuckles":
                return new BrassKnuckles();
            case "spikedGloves":
                return new SpikedGloves();
            case "swordHands":
                return new SwordHands();
            case "charm":
                return new Charm();
            case "sleep":
                return new Insomnia();
            case "seppuku":
                return new Seppuku();
            case "key":
                return new Key();
            case "blueLightsaber":
                return new BlueLightsaber();
            case "mjolnir":
                return new Mjolnir();
            case "thunderBlade":
                return new ThunderBlade();
            case "pizza":
                return new Pizza();
            case "snowLauncher":
                return new SnowLauncher();
            case "snowShuriken":
                return new SnowShuriken();
            case "staffItem":
                return new StaffItem();
            case "inquisitorLightsaber":
                return new InquisitorLightsaber();
            case "jeweledCutlass":
                return new JeweledCutlass();
            case "waterHammer":
                return new WaterHammer();
            case "healthPotion":
                return new HealthPotion();
            case "manaPotion":
                return new ManaPotion();
            case "xpPotion":
                return new XPPotion();
            default:
                return new Key();
        }
    }

    private int deserializeLevel(JSONObject entityJSON){
        return entityJSON.getInt("Level");
    }

    private int deserializeHP(JSONObject entityJSON){
        return entityJSON.getInt("HP");
    }

    private int deserializeMaxHP(JSONObject entityJSON){
        return entityJSON.getInt("MaxHP");
    }

    private int deserializeMana(JSONObject playerJSON){
        return playerJSON.getInt("Mana");
    }

    private int deserializeXP(JSONObject playerJSON){
        return playerJSON.getInt("XP");
    }

    private int deserializeGold(JSONObject playerJSON){
        return playerJSON.getInt("Gold");
    }

}