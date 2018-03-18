package Controller.SavingLoading;

import Model.Entity.*;
import Model.Entity.Role.*;
import Model.Entity.NPC.*;
import Model.Entity.Skill.*;

import Model.Map.*;
import Model.Map.AreaEffect.*;
import Model.Map.Terrain.*;

import Model.Item.*;
import Model.Item.TakeableItem.*;
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

import View.StatusView.StatusViewPort;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    private JSONObject saveFileJSON;
    private Player player;
    private List<NPC> NPCs;

    public Deserializer(GameBuilder gameBuilder, JSONObject saveFileJSON){
        this.saveFileJSON = saveFileJSON;
        NPCs = new ArrayList<NPC>();
        deserializeWorld(saveFileJSON.getJSONObject("World"));

        gameBuilder.setPlayer(player);
        gameBuilder.setStatusViewPort(new StatusViewPort(player, player.getEquipment(), player.getInventory(),player.getRole()));

    }

    public void deserializeWorld(JSONObject worldJSON){
        World world = World.getWorld();

        JSONArray mapsJSON = worldJSON.getJSONArray("Maps");

        for(int mapIndex = 0; mapIndex < mapsJSON.length(); mapsJSON.length()){
            deserializeMap(mapsJSON.getJSONObject(mapIndex));
        }

    }

    private Map deserializeMap(JSONObject mapJSON){
        String mapID = mapJSON.getString("mapID");
        int rows = mapJSON.getInt("Rows");
        int cols = mapJSON.getInt("Cols");

        JSONArray locationsJSON = mapJSON.getJSONArray("Locations");
        Location[][] locations = new Location[rows][cols];
        for(int locationIndex = 0; locationIndex < locationsJSON.length(); locationIndex++){
            Location location = deserializeLocation(locationsJSON.getJSONObject(locationIndex));
            locations[location.getyCoordinate()][location.getxCoordinate()] = location;
        }
        Map map = new Map(locations);

        JSONArray entitiesJSON  = mapJSON.getJSONArray("Entities");
        for(int entityIndex = 0; entityIndex < entitiesJSON.length(); entityIndex++){

            Entity currEntity = deserializeEntity(entitiesJSON.getJSONObject(entityIndex));
            int currEntityX = entitiesJSON.getJSONObject(entityIndex).getInt("X");
            int currEntityY = entitiesJSON.getJSONObject(entityIndex).getInt("Y");

            map.setEntityLocation(locations[currEntityY][currEntityX], currEntity);
        }

        return map;
    }

    public Entity deserializeEntity(JSONObject entityJSON){

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

    private Location deserializeLocation(JSONObject locationJSON){

        //TERRAIN DESERIALIZATION
        Terrain terrain;
        String terrainType = locationJSON.getString("Terrain");
        if(terrainType.equals("ICE")){
            terrain = new Ice();
        }
        else if(terrainType.equals("GLACIER")){
            terrain = new Glacier();
        }
        else{
            terrain = new Water();
        }


        //AREAEFFECT DESERIALIZATION
        AreaEffect areaEffect;
        JSONObject areaEffectJSON = locationJSON.getJSONObject("AreaEffect");
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


        //OBSTACLE DESERIALIZATION
        boolean obstacle = locationJSON.getBoolean("Obstacle");


        //ITEMS DESERIALIZATION
        List<Item> items = new ArrayList<>();
        JSONArray jsonItems = locationJSON.getJSONArray("Items");
        for(int itemIndex = 0; itemIndex < jsonItems.length(); itemIndex++){
            String itemType = jsonItems.getString(itemIndex);
            TakeableItem takeableItem = parseItem(itemType);
            items.add(takeableItem);
        }

        //X,Y DESERIALIZATION
        int X = locationJSON.getInt("X");
        int Y = locationJSON.getInt("Y");

        Location location = new Location(terrain, obstacle, areaEffect, items);
        location.setxCoordinate(X);
        location.setyCoordinate(Y);

        return location;
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
            newEquipment.equip(parseItem(hotbarItems.getString(i)));
        }
        String head = equipment.getString("Head");
        if(!head.equals("none")) {
            newEquipment.equip(parseItem(head));
        }
        String body = equipment.getString("Body");
        if(!body.equals("none")) {
            newEquipment.equip(parseItem(body));
        }
        String legs = equipment.getString("Legs");
        if(!legs.equals("none")) {
            newEquipment.equip(parseItem(legs));
        }
        String ring = equipment.getString("Ring");
        if(!ring.equals("none")) {
            newEquipment.equip(parseItem(ring));
        }

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