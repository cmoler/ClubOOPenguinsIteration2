package Controller.SavingLoading;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.Staff;
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
import Model.Item.TakeableItem.Projectile.AngularProjectile;
import Model.Item.TakeableItem.Projectile.LinearProjectile;
import Model.Item.TakeableItem.Projectile.RadialProjectile;
import Model.Item.TakeableItem.RangedWeaponItem.Pizza;
import Model.Item.TakeableItem.RangedWeaponItem.SnowLauncher;
import Model.Item.TakeableItem.RangedWeaponItem.SnowShuriken;
import Model.Item.TakeableItem.StaffItem.StaffItem;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TwoHandedWeaponItem.InquisitorLightsaber;
import Model.Item.TakeableItem.TwoHandedWeaponItem.JeweledCutlass;
import Model.Item.TakeableItem.TwoHandedWeaponItem.WaterHammer;
import Model.Item.TakeableItem.UseableItem;
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
    JSONObject player = new JSONObject();
    JSONObject playerRole = new JSONObject();

    public void serializePlayer(Player player) {
        this.player.put("Role", saveRole(player.getRole()));
        this.player.put("Level", player.getLevel());
        this.player.put("Location", saveLocation(player.getLocation()));
        this.player.put("Inventory", saveInventory(player.getInventory()));
        this.player.put("Equipment", saveEquipment(player.getEquipment()));
        this.player.put("EntityType", player.getEntityType().toString());
        this.player.put("HP", player.getHealth());
        this.player.put("MaxHP", player.getMaxHealth());
        this.player.put("Mana", player.getMana());
        this.player.put("XP", player.getExperience());
        this.player.put("Gold", player.getGold());
    }

    public void serializeWorld(World world) {
        this.world.put("CurrentMap", world.getCurrentMap());

        ArrayList<JSONObject> mapJSONS = new ArrayList<>();
        for (String str: world.getMaps().keySet() ) {
            mapJSONS.add(saveMap(world.getMap(str)));
        }
        this.world.put("Maps", new JSONArray(mapJSONS));
    }


    private JSONObject saveEntity(Entity entity){
        JSONObject entityJSON = new JSONObject();
        entityJSON.put("Level", entity.getLevel());
        entityJSON.put("Location", saveLocation(entity.getLocation()));
        entityJSON.put("Inventory", saveInventory(entity.getInventory()));
        entityJSON.put("EntityType", entity.getEntityType().toString());
        entityJSON.put("HP", entity.getHealth());
        entityJSON.put("MaxHP", entity.getMaxHealth());

        //Save either as player or as NPC
        if (entity.getName() == "npc") {
            entityJSON.put("Name", "npc");
            saveNPC((NPC) entity, entityJSON);
        }
        else{
            entityJSON.put("Name", "player");
            savePlayer((Player) entity, entityJSON);
        }

        return entityJSON;
    }


    public void savePlayer(Player player, JSONObject entityJSON) {

        //TODO save player stuff


//        this.player.put("Role", saveRole(player.getRole()));
//        this.player.put("Level", player.getLevel());
//        this.player.put("Location", saveLocation(player.getLocation()));
//        this.player.put("Inventory", saveInventory(player.getInventory()));
//        this.player.put("Equipment", saveEquipment(player.getEquipment()));
//        this.player.put("EntityType", player.getEntityType().toString());
//        this.player.put("HP", player.getHealth());
//        this.player.put("MaxHP", player.getMaxHealth());
//        this.player.put("Mana", player.getMana());
//        this.player.put("XP", player.getExperience());
//        this.player.put("Gold", player.getGold());
    }

    public void saveNPC(NPC npc, JSONObject entityJSON){

        //TODO save NPC stuff

    }



    private JSONObject saveMap(Map map) {
        JSONObject mapJSON = new JSONObject();
        mapJSON.put("MapID", map.getMapID());
        mapJSON.put("Rows", map.getRows());
        mapJSON.put("Cols", map.getCols());
        mapJSON.put("EntityLocations:", saveEntityLocation(map.getEntityLocationList()));
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
        JSONObject areaEffectJSON = new JSONObject();
        locationJSON.put("Terrain", ""+location.getTerrain().getTerrainType());
        locationJSON.put("Obstacle", location.hasObstacle());
        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 0; i < location.getItems().size(); i++){

        locationJSON.put("AreaEffect", saveAreaEffect(location.getAreaEffect()));
            itemList.add(location.getItems().get(i).getName());
        }
        locationJSON.put("Items", itemList);
        return locationJSON;
    }

    private JSONObject saveAreaEffect(AreaEffect areaEffect){
        JSONObject areaEffectJSON = new JSONObject();
        areaEffectJSON.put("Type", areaEffect.getAreaEffectType());
        if(areaEffect.getAreaEffectType() == AreaEffectType.TELEPORT){
            areaEffectJSON.put("mapID", ((TeleportAreaEffect) areaEffect).getMapID());
            areaEffectJSON.put("X", ((TeleportAreaEffect) areaEffect).getX());
            areaEffectJSON.put("Y", ((TeleportAreaEffect) areaEffect).getY());
        }
        return areaEffectJSON;
    }

    private JSONObject saveEntityLocation(EntityLocation entityLocation){
        //get entitylocationlist hashmap list or iterator
        JSONObject entityLocationJSON = new JSONObject();
        ArrayList<JSONObject> entityLocationList = new ArrayList<>();
        ArrayList<Pair> entityLocationPair = entityLocation.getAssociations();
        for (int i = 0; i < entityLocationPair.size(); i++){
            Entity entity = (Entity) entityLocationPair.get(i).getKey();
            Location location = (Location) entityLocationPair.get(i).getValue();
            JSONObject pairJSON = new JSONObject();
            pairJSON.put("Entity", saveEntity(entity));
            pairJSON.put("LocationX", location.getxCoordinate());
            pairJSON.put("LocationY", location.getyCoordinate());
            entityLocationList.add(pairJSON);
        }
        entityLocationJSON.put("Pairs", new JSONArray(entityLocationList));
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

    //DANGER
    private JSONObject saveInventory(Inventory inventory) {
        JSONObject inventoryJSON = new JSONObject();
        JSONArray items = new JSONArray();
        for(int i = 0;inventory.getIterator().hasNext();i++, inventory.getIterator().next()){
            items.put(inventory.getIterator().getCurrent().save(this));
        }
        inventoryJSON.put("Items:", new JSONArray(items));
        return inventoryJSON;
    }

    //DANGER
    private JSONObject saveEquipment(Equipment equipment) {
        JSONObject equipmentJSON = new JSONObject();
        equipmentJSON.put("Hotbar", saveHotbar(equipment.getHotbarItems()));
        equipmentJSON.put("Head", equipment.getHead().save(this));
        equipmentJSON.put("Body", equipment.getBody().save(this));
        equipmentJSON.put("Legs", equipment.getLegs().save(this));
        equipmentJSON.put("Ring", equipment.getRing().save(this));
        return equipmentJSON;
    }

    private JSONObject saveHotbar(List<TakeableItem> items){
        JSONObject hotbarJSON = new JSONObject();
        JSONArray itemsList = new JSONArray();
        for(int i =0; i < items.size(); i++) {
            itemsList.put(items.get(i).save(this));
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

    public JSONObject getPlayer() {
        return player;
    }
}
