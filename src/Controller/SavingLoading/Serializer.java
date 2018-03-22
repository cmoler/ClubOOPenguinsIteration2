package Controller.SavingLoading;

import Model.Entity.NPC.NPC;
import Model.Entity.NPC.ShopKeepNPC;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Item.InteractiveItem.ChestInteractiveItem;
import Model.Item.InteractiveItem.InteractiveItem;
import Model.Item.OneShotItem.GoldOneShotItem;
import Model.Item.OneShotItem.HealingOneShotItem;
import Model.Item.OneShotItem.ManaOneShotItem;
import Model.Item.OneShotItem.XPOneShotItem;
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
import Model.Item.TakeableItem.WearableItem;
import Model.Map.AreaEffect.AreaEffect;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.AreaEffect.TeleportAreaEffect;
import Model.Map.EntityLocation;
import javafx.util.Pair;
import org.json.*;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import java.util.ArrayList;
import java.util.List;

public class Serializer implements Saver{
    JSONObject world = new JSONObject();
    JSONObject playerRole = new JSONObject();

    public void serializeWorld(World world) {
        this.world.put("CurrentMap", world.getCurrentMap().getMapID());

        ArrayList<JSONObject> mapJSONS = new ArrayList<>();
        for (String str: world.getMaps().keySet() ) {
            mapJSONS.add(saveMap(world.getMap(str)));
        }
        this.world.put("Maps", new JSONArray(mapJSONS));
    }


    private JSONObject saveEntity(Entity entity){
        JSONObject entityJSON = new JSONObject();
        entityJSON.put("Inventory", saveInventory(entity.getInventory()));
        entityJSON.put("EntityType", entity.getEntityType().name());
        entityJSON.put("HP", entity.getHealth());
        entityJSON.put("MaxHP", entity.getMaxHealth());
        entityJSON.put("LocationX", entity.getLocation().getxCoordinate());
        entityJSON.put("LocationY", entity.getLocation().getyCoordinate());
        //Save either as player or as NPC
        JSONObject classJSON;
        if (entity.getName().equals("NPC")) {
            classJSON = saveNPC((NPC) entity);
        }
        else if (entity.getName().equals("ShopKeepNPC")){
            classJSON = saveShopKeeper((ShopKeepNPC) entity);
        }
        else {
            classJSON = savePlayer((Player) entity);
        }
        entityJSON.put("EntityClass", classJSON);

        return entityJSON;
    }


    public JSONObject savePlayer(Player player) {
        JSONObject playerJSON = new JSONObject();

        playerJSON.put("Name", "Player");
        playerJSON.put("Role", saveRole(player.getRole()));
        playerJSON.put("SkillPoints", player.getSkillPointsAvailable());
        playerJSON.put("Equipment", saveEquipment(player.getEquipment()));
        playerJSON.put("Mana", player.getMana());
        playerJSON.put("XP", player.getExperience());
        playerJSON.put("Gold", player.getGold());

        return playerJSON;
    }

    public JSONObject saveNPC(NPC npc){
        JSONObject NPCJSON = new JSONObject();

        NPCJSON.put("Name", "NPC");
        NPCJSON.put("NPCState", npc.getState());
        NPCJSON.put("Color", npc.getColor());

        return NPCJSON;
    }

    public JSONObject saveShopKeeper(ShopKeepNPC shopKeepNPC){
        JSONObject shopKeepNPCJSON = new JSONObject();

        shopKeepNPCJSON.put("Name", "ShopKeepNPC");
        shopKeepNPCJSON.put("NPCState", shopKeepNPC.getState());
        shopKeepNPCJSON.put("Color", shopKeepNPC.getColor());
        JSONObject shopMapJSON = new JSONObject();
        shopMapJSON.put("MapID", shopKeepNPC.getMapID());
        shopMapJSON.put("X", shopKeepNPC.getLocationI());
        shopMapJSON.put("Y", shopKeepNPC.getLocationJ());

        shopKeepNPCJSON.put("ShopMap", shopMapJSON );

        return shopKeepNPCJSON;
    }



    private JSONObject saveMap(Map map) {
        JSONObject mapJSON = new JSONObject();
        mapJSON.put("mapID", map.getMapID());
        mapJSON.put("Rows", map.getRows());
        mapJSON.put("Cols", map.getCols());
        mapJSON.put("Entities", saveEntityLocation(map.getEntityLocationList()));
        Location locations[][] = map.getLocations();
        ArrayList<JSONObject> locationsJSONObjects = new ArrayList<>();
        for(int i = 0; i < locations.length; i++ ){
            for(int j = 0; j < locations[i].length; j++) {
                locationsJSONObjects.add(saveLocation(locations[i][j]));
            }
        }
        mapJSON.put("Locations", new JSONArray(locationsJSONObjects));
        return mapJSON;
    }

    private JSONObject saveLocation(Location location) {
        JSONObject locationJSON = new JSONObject();
        locationJSON.put("Terrain", ""+location.getTerrain().getTerrainType());
        locationJSON.put("Obstacle", (location.hasObstacle() ? 1 : 0));
        locationJSON.put("AreaEffect", saveAreaEffect(location.getAreaEffect()));
        locationJSON.put("X", location.getxCoordinate());
        locationJSON.put("Y", location.getyCoordinate());
        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 0; i < location.getItems().size(); i++){

            itemList.add(location.getItems().get(i).getName());
        }
        locationJSON.put("Items", itemList);
        return locationJSON;
    }

    private JSONObject saveAreaEffect(AreaEffect areaEffect){
        JSONObject areaEffectJSON = new JSONObject();

        areaEffectJSON.put("Type", nullChecker(areaEffect));
        if (areaEffect != null){
            if(areaEffect.getAreaEffectType() == AreaEffectType.TELEPORT){
                areaEffectJSON.put("mapID", ((TeleportAreaEffect) areaEffect).getMapID());
                areaEffectJSON.put("X", ((TeleportAreaEffect) areaEffect).getX());
                areaEffectJSON.put("Y", ((TeleportAreaEffect) areaEffect).getY());
            }
        }
        return areaEffectJSON;
    }

    private JSONArray saveEntityLocation(EntityLocation entityLocation){
        //get entitylocationlist hashmap list or iterator
        ArrayList<JSONObject> entityLocationList = new ArrayList<>();
        ArrayList<Pair> entityLocationPair = entityLocation.getAssociations();
        for (int i = 0; i < entityLocationPair.size(); i++){
            Entity entity = (Entity) entityLocationPair.get(i).getValue();
            entityLocationList.add(saveEntity(entity));
        }
        JSONArray entityLocationJSON = new JSONArray(entityLocationList);
        return entityLocationJSON;
    }

    private JSONObject saveRole(Role role){
        JSONObject roleJSON = new JSONObject();
        roleJSON.put("BindWoundsLevel", role.getBindWounds());
        roleJSON.put("BargainLevel", role.getBargain());
        roleJSON.put("ObservationLevel", role.getObservation());
        role.save(this);
        roleJSON.put("Role", playerRole);
        return roleJSON;
    }

    private JSONObject saveInventory(Inventory inventory) {
        JSONObject inventoryJSON = new JSONObject();
        ArrayList<String> items = new ArrayList<>();
        for(Inventory.InventoryIterator inventoryIterator = inventory.getIterator();inventoryIterator.hasNext();inventoryIterator.next()){
            items.add(inventoryIterator.getCurrent().save(this));
        }
        inventoryJSON.put("Items", new JSONArray(items));
        return inventoryJSON;
    }

    private JSONObject saveEquipment(Equipment equipment) {
        JSONObject equipmentJSON = new JSONObject();
        equipmentJSON.put("Hotbar", saveHotbar(equipment.getHotbarItems()));
        equipmentJSON.put("Head", nullChecker(equipment.getHead()));
        equipmentJSON.put("Body", nullChecker(equipment.getBody()));
        equipmentJSON.put("Legs", nullChecker(equipment.getLegs()));
        equipmentJSON.put("Ring", nullChecker(equipment.getRing()));
        return equipmentJSON;
    }

    private String nullChecker(WearableItem object){
        if(object == null){
            return "";
        }
        else{
            return object.save(this);
        }
    }

    private String nullChecker(AreaEffect areaEffect){
        if(areaEffect == null){
            return "";
        }
        else{
            return areaEffect.getAreaEffectType().name();
        }
    }

    private JSONObject saveHotbar(List<TakeableItem> items){
        JSONObject hotbarJSON = new JSONObject();
        ArrayList<String> itemsList = new ArrayList<>();
        for(int i =0; i < items.size(); i++) {
            itemsList.add(items.get(i).save(this));
        }
        hotbarJSON.put("Items", new JSONArray(itemsList));
        return hotbarJSON;
    }

    @Override
    public void saveSmasher(Smasher role) {
        playerRole.put("RoleName", "Smasher");
        playerRole.put("OneHandedWeaponLevel", role.getOneHandedWeapon());
        playerRole.put("TwoHandedWeaponLevel", role.getTwoHandedWeapon());
        playerRole.put("BrawlLevel", role.getBrawl());
    }

    @Override
    public void saveSummoner(Summoner role) {
        playerRole.put("RoleName", "Summoner");
        playerRole.put("BaneLevel", role.getBane());
        playerRole.put("BoonLevel", role.getBoon());
        playerRole.put("EnchantmentLevel", role.getEnchantment());
        playerRole.put("StaffLevel", role.getStaff());
    }

    @Override
    public void saveSneak(Sneak role) {
        playerRole.put("RoleName", "Sneak");
        playerRole.put("CreepLevel", role.getCreep());
        playerRole.put("DetectAndRemoveTrapLevel", role.getDetectAndRemoveTrap());
        playerRole.put("PickPocketLevel", role.getPickPocket());
        playerRole.put("RangedWeaponLevel", role.getRangedWeapon());
    }



    @Override
    public String saveInteractiveItem(InteractiveItem interactiveItem) {
        return interactiveItem.getName();
    }

    @Override
    public String saveGoldOneShotItem(GoldOneShotItem goldOneShotItem) {
        return goldOneShotItem.getName();
    }

    @Override
    public String saveHealingOneShotItem(HealingOneShotItem healingOneShotItem) {
        return healingOneShotItem.getName();
    }

    @Override
    public String saveManaOneShotItem(ManaOneShotItem manaOneShotItem) {
        return manaOneShotItem.getName();
    }

    @Override
    public String saveXPOneShotItem(XPOneShotItem xpOneShotItem) {
        return xpOneShotItem.getName();
    }

    @Override
    public String saveBody(Body body) {
        return body.getName();
    }

    @Override
    public String saveHelmet(Helmet helmet) {
        return helmet.getName();
    }

    @Override
    public String saveLeg(Leg leg) {
        return leg.getName();
    }

    @Override
    public String saveRing(Ring ring) {
        return ring.getName();
    }

    @Override
    public String saveAngulariceAttack(AngularIceAttack angularIceAttack) {
        return angularIceAttack.getName();
    }

    @Override
    public String saveLinearIceAttack(LinearIceAttack linearIceAttack) {
        return linearIceAttack.getName();
    }

    @Override
    public String saveRadialIceBomb(RadialIceBomb radialIceBomb) {
        return radialIceBomb.getName();
    }

    @Override
    public String saveHeal(Heal heal) {
        return heal.getName();
    }

    @Override
    public String saveIncreaseMaxHealth(IncreaseMaxHealth increaseMaxHealth) {
        return increaseMaxHealth.getName();
    }

    @Override
    public String saveIncreaseXP(IncreaseXP increaseXP) {
        return increaseXP.getName();
    }

    @Override
    public String saveBrassKnuckles(BrassKnuckles brassKnuckles) {
        return brassKnuckles.getName();
    }

    @Override
    public String saveSpikedGloves(SpikedGloves spikedGloves) {
        return spikedGloves.getName();
    }

    @Override
    public String saveSwordHands(SwordHands swordHands) {
        return swordHands.getName();
    }

    @Override
    public String saveCharm(Charm charm) {
        return charm.getName();
    }

    @Override
    public String saveInsomnia(Insomnia insomnia) {
        return insomnia.getName();
    }

    @Override
    public String saveSeppuku(Seppuku seppuku) {
        return seppuku.getName();
    }

    @Override
    public String saveBlueLightSaber(BlueLightsaber blueLightsaber) {
        return blueLightsaber.getName();
    }

    @Override
    public String saveMjolnir(Mjolnir mjolnir) {
        return mjolnir.getName();
    }

    @Override
    public String saveThunderBlade(ThunderBlade thunderBlade) {
        return thunderBlade.getName();
    }

    @Override
    public String saveHealthPotion(HealthPotion healthPotion) {
        return healthPotion.getName();
    }

    @Override
    public String saveManaPotion(ManaPotion manaPotion) {
        return manaPotion.getName();
    }

    @Override
    public String saveXPPotion(XPPotion xpPotion) {
        return xpPotion.getName();
    }

    @Override
    public String savePizza(Pizza pizza) {
        return pizza.getName();
    }

    @Override
    public String saveSnowLauncher(SnowLauncher snowLauncher) {
        return snowLauncher.getName();
    }

    @Override
    public String saveSnowShuriken(SnowShuriken snowShuriken) {
        return snowShuriken.getName();
    }

    @Override
    public String saveStaff(StaffItem staff) {
        return staff.getName();
    }

    @Override
    public String saveInquisitorLightsaber(InquisitorLightsaber inquisitorLightsaber) {
        return inquisitorLightsaber.getName();
    }

    @Override
    public String saveJeweledCutlass(JeweledCutlass jeweledCutlass) {
        return jeweledCutlass.getName();
    }

    @Override
    public String saveWaterHammer(WaterHammer waterHammer) {
        return waterHammer.getName();
    }

    @Override
    public String saveKey(Key key) {
        return key.getName();
    }

    @Override
   public String saveChestInteractiveItem(ChestInteractiveItem chestInteractiveItem) {
        if(chestInteractiveItem.isOpened()){
            return chestInteractiveItem.getName()+"Open";
        }
        else {
            return chestInteractiveItem.getName()+"Closed";
        }

    }

    public JSONObject getWorld() {
        return world;
    }
}
