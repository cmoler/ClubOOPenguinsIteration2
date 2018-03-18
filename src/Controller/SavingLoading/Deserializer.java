package Controller.SavingLoading;

import Configs.ImagesInfo;
import Model.Entity.*;
import Model.Entity.NPC.NPC;
import Model.Entity.NPC.ShopKeepNPC;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.*;

import Model.Item.InteractiveItem.ChestInteractiveItem;
import Model.Map.*;
import Model.Map.AreaEffect.*;

import Model.Map.Location;
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

import View.AreaView.*;
import View.AreaView.ItemView;
import View.StatusView.StatusViewPort;

import View.Viewport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Deserializer {

    private JSONObject saveFileJSON;

    private GameBuilder gameBuilder;
    private Player player;
    private List<NPC> NPCs;

    private Viewport viewport = new Viewport();
    private HashMap<Map,MapView> mapViews = new HashMap<>();
    private MapView currentMapView;
    private WorldView worldView;
    private StatusViewPort statusViewPort;
    private AreaViewPort areaViewPort = new AreaViewPort();


    public Deserializer(GameBuilder gameBuilder, JSONObject saveFileJSON){
        this.saveFileJSON = saveFileJSON;
        this.gameBuilder = gameBuilder;
        NPCs = new ArrayList<>();

        viewport.add(areaViewPort);

        deserializeWorld(saveFileJSON.getJSONObject("World"));

        statusViewPort = new StatusViewPort(player);
        viewport.add(statusViewPort);
        gameBuilder.setStatusViewPort(statusViewPort);
        gameBuilder.setAreaViewport(areaViewPort);
        gameBuilder.setPlayer(player);
    }

    public void deserializeWorld(JSONObject worldJSON){
        World world = World.getWorld();

        JSONArray mapsJSON = worldJSON.getJSONArray("Maps");

        for(int mapIndex = 0; mapIndex < mapsJSON.length(); mapsJSON.length()){
            deserializeMap(mapsJSON.getJSONObject(mapIndex));
        }

        setNPC(this.NPCs, this.player);

        Iterator<Map> maps = mapViews.keySet().iterator();

        while (maps.hasNext()) {
            Map currentMap = maps.next();
            mapViews.get(currentMap).setEntity(player);
        }

        worldView = new WorldView(mapViews);

        areaViewPort.add(worldView);

        viewport.add(areaViewPort);
    }

    private Map deserializeMap(JSONObject mapJSON){
        String mapID = mapJSON.getString("mapID");
        int rows = mapJSON.getInt("Rows");
        int cols = mapJSON.getInt("Cols");

        JSONArray locationsJSON = mapJSON.getJSONArray("Locations");
        MapView mapView = new MapView();
        currentMapView = mapView;

        Location[][] locations = new Location[rows][cols];
        MapView mapView = new MapView();
        currentMapView = mapView;

        for(int locationIndex = 0; locationIndex < locationsJSON.length(); locationIndex++){
            Location location = deserializeLocation(locationsJSON.getJSONObject(locationIndex));
            locations[location.getyCoordinate()][location.getxCoordinate()] = location;
        }
        Map map = new Map(locations);

        World.getWorld().addMap(map.getMapID(), map);
        World.getWorld().changeCurrentMapTo(World.getWorld().getMap(mapID));

        JSONArray entitiesJSON  = mapJSON.getJSONArray("Entities");
        for(int entityIndex = 0; entityIndex < entitiesJSON.length(); entityIndex++){

            Entity currEntity = deserializeEntity(entitiesJSON.getJSONObject(entityIndex));
            int currEntityX = entitiesJSON.getJSONObject(entityIndex).getInt("LocationX");
            int currEntityY = entitiesJSON.getJSONObject(entityIndex).getInt("LocationY");

            map.setEntityLocation(locations[currEntityY][currEntityX], currEntity);
        }

        mapViews.put(map, mapView);

        return map;
    }


    private Entity deserializeEntity(JSONObject entityJSON){

        Inventory inventory = deserializeInventory(entityJSON.getJSONObject("Inventory"));
        EntityType entityType = deserializeEntityType(entityJSON);
        int HP = deserializeHP(entityJSON);
        int maxHP = deserializeMaxHP(entityJSON);

        JSONObject entityClass = entityJSON.getJSONObject("EntityClass");
        String name = entityClass.getString("Name");

        Entity entity;

        switch (name) {
            case "Player":
                entity = deserializePlayer(entityClass, entityType);
                break;
            case "NPC":
                entity = deserializeNPC(entityClass, entityType);
                break;
            case "ShopKeepNPC":
                entity = deserializeShopKeepNPC(entityClass, entityType);
                break;
            default:
                entity = null;
        }

        entity.modifyMaxHealth(maxHP - 100);
        entity.setHealth(HP);
        entity.setInventory(inventory);

        return entity;
    }

    private Entity deserializePlayer(JSONObject EntityClass, EntityType type) {
        int skillPointsAvailable = EntityClass.getInt("SkillPoints");
        JSONObject roleJSON = EntityClass.getJSONObject("Role");

        Role role = deserializeRole(roleJSON);

        Player player = new Player(role, type, skillPointsAvailable);

        player.addMana(EntityClass.getInt("Mana"));
        player.gainExperience(EntityClass.getInt("XP"));
        player.modifyGold(EntityClass.getInt("Gold"));

        deserializeEquipment(EntityClass.getJSONObject("Equipment"), player);

        PlayerView playerView = new PlayerView(player);
        areaViewPort.add(playerView);

        this.player = player;
        return player;

    }

    private Entity deserializeNPC(JSONObject entityClass, EntityType entityType){

        String color = entityClass.getString("color");
        NPC npc = new NPC(color, entityType);
        deserializeNPCState(entityClass, npc);

        NPCs.add(npc);

        NPCView npcView = new NPCView(npc);
        currentMapView.add(npcView);

        return npc;
    }

    private Entity deserializeShopKeepNPC(JSONObject entityClass, EntityType entityType){
        String color = entityClass.getString("color");
        JSONObject shopMap = entityClass.getJSONObject("ShopMap");
        String mapID = shopMap.getString("MapID");
        int i = shopMap.getInt("I");
        int j= shopMap.getInt("J");

        ShopKeepNPC shopKeepNPC = new ShopKeepNPC(color, mapID, i, j, entityType);
        deserializeNPCState(entityClass, shopKeepNPC);

        NPCs.add(shopKeepNPC);

        NPCView npcView = new NPCView(shopKeepNPC);
        currentMapView.add(npcView);

        return shopKeepNPC;
    }

    private void setNPC(List<NPC> NPCs, Player player){
        for (NPC npc : NPCs) {
            npc.setPlayer(player);
        }
    }

    private void deserializeNPCState(JSONObject entityClass, NPC npc){
        String npcState = entityClass.getString("NPCState");

        switch (npcState){
            case "aggro":
                npc.pissOff();
                break;
            case "enemy":
                npc.pissOff();
                break;
            case "sleep":
                npc.fallAsleep();
                break;
            case "friendly":
                npc.beFriends();
                break;
        }

    }

    private EntityType deserializeEntityType(JSONObject entityJSON){
        if(entityJSON.getString("EntityType").equals("ICE")){
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

        TerrainView terrainView;
        if(terrainType.equals("ICE")){
            terrain = new Ice();
            terrainView = new TerrainView(ImagesInfo.ICE_IMAGE);
        }
        else if(terrainType.equals("GLACIER")){
            terrain = new Glacier();
            terrainView = new TerrainView(ImagesInfo.GLACIER_IMAGE);
        }
        else{
            terrain = new Water();
            terrainView = new TerrainView(ImagesInfo.WATER_IMAGE);
        }


        //AREAEFFECT DESERIALIZATION
        AreaEffect areaEffect;
        JSONObject areaEffectJSON = locationJSON.getJSONObject("AreaEffect");
        String areaEffectType = areaEffectJSON.getString("Type");
        Viewport areaEffectView = null;
        DecalView decalView = null;

        if(areaEffectType.equals("DAMAGE")){
            areaEffect = new DamageAreaEffect();

            areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_DAMAGE_IMAGE);

        }
        else if(areaEffectType.equals("HEAL")){
            areaEffect = new HealAreaEffect();

            areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_HEAL_IMAGE);
            decalView = new DecalView(ImagesInfo.RED_CROSS_IMAGE);
        }
        else if(areaEffectType.equals("KILL")){
            areaEffect = new KillAreaEffect();

            areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_KILL_IMAGE);
            decalView = new DecalView(ImagesInfo.SKULL_CROSS_BONES_IMAGE);
        }
        else if(areaEffectType.equals("LEVELUP")){
            areaEffect = new LevelUpAreaEffect();

            areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_LEVELUP_IMAGE);
            decalView = new DecalView(ImagesInfo.GOLD_STAR_IMAGE);
        }
        else if(areaEffectType.equals("TELEPORT")){
            String mapID = areaEffectJSON.getString("mapID");
            int X = areaEffectJSON.getInt("X");
            int Y = areaEffectJSON.getInt("Y");
            areaEffect = new TeleportAreaEffect(mapID,X,Y);

            areaEffectView = new AreaEffectView(ImagesInfo.ITEM_TELEPORTER_IMAGE);
        }
        else if(areaEffectType.equals("TRAP")){
            areaEffect = new TrapAreaEffect();
            areaEffectView = new TrapView((TrapAreaEffect) areaEffect);
        }
        else if(areaEffectType.equals("TRANSACTION")){
            areaEffect = new TransactionAreaEffect();
            areaEffectView = new TransactionAreaEffectView((TransactionAreaEffect) areaEffect);
        }
        else{
            areaEffect = null;
            areaEffectView = null;
        }


        //OBSTACLE DESERIALIZATION
        boolean obstacle = locationJSON.getInt("Obstacle") == 1;


        //ITEMS DESERIALIZATION
        List<Item> items = new ArrayList<>();
        List<ItemView> itemViews = new ArrayList<>();
        JSONArray jsonItems = locationJSON.getJSONArray("Items");
        for(int itemIndex = 0; itemIndex < jsonItems.length(); itemIndex++){
            String itemType = jsonItems.getString(itemIndex);
            Item item = parseItem(itemType);
            items.add(item);
            itemViews.add(new ItemView(itemType));
        }

        //X,Y DESERIALIZATION
        int X = locationJSON.getInt("X");
        int Y = locationJSON.getInt("Y");

        Location location = new Location(terrain, obstacle, areaEffect, items);
        location.setxCoordinate(X);
        location.setyCoordinate(Y);


        LocationView locationView = new LocationView(location, location.getxCoordinate(), location.getyCoordinate());
        for(ItemView itemView : itemViews){
            locationView.add(itemView);
        }

        locationView.add(areaEffectView);
        locationView.add(decalView);
        locationView.add(terrainView);

        currentMapView.add(locationView);

        if(obstacle){
            locationView.add(new ObstacleView(ImagesInfo.OBSTACLE_IMAGE));
        }

        return location;
    }


    private Inventory deserializeInventory(JSONObject inventory){
        Inventory inventoryModel = new Inventory();
        JSONArray items = inventory.getJSONArray("Items");
        for(int i = 0; i < items.length(); ++i){
            Object item = items.get(i);
            TakeableItem takeableItem = (TakeableItem) parseItem(item.toString());
            inventoryModel.addItem(takeableItem);
        }

        return inventoryModel;
    }

    private void deserializeEquipment(JSONObject equipment, Player player){

        Equipment newEquipment = player.getEquipment();

        JSONObject hotbar = equipment.getJSONObject("Hotbar");
        JSONArray hotbarItems = hotbar.getJSONArray("Items");
        for (int i = 0; i < hotbarItems.length(); i++) {
            newEquipment.equip((TakeableItem) parseItem(hotbarItems.getString(i)));
        }
        String head = equipment.getString("Head");
        if(!head.equals("")) {
            newEquipment.equip((TakeableItem) parseItem(head));
        }
        String body = equipment.getString("Body");
        if(!body.equals("")) {
            newEquipment.equip((TakeableItem) parseItem(body));
        }
        String legs = equipment.getString("Legs");
        if(!legs.equals("")) {
            newEquipment.equip((TakeableItem) parseItem(legs));
        }
        String ring = equipment.getString("Ring");
        if(!ring.equals("")) {
            newEquipment.equip((TakeableItem) parseItem(ring));
        }

    }



    private Item parseItem(String itemName){
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
            case "chestInteractiveItemOpen":
                return new ChestInteractiveItem(true);
            case "chestInteractiveItemClosed":
                return new ChestInteractiveItem(false);
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