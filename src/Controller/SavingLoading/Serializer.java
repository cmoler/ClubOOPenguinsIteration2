package Controller.SavingLoading;

import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.Staff;
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
import Model.Item.TakeableItem.TwoHandedWeaponItem.InquisitorLightsaber;
import Model.Item.TakeableItem.TwoHandedWeaponItem.JeweledCutlass;
import Model.Item.TakeableItem.TwoHandedWeaponItem.WaterHammer;
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

public class Serializer implements Saver{
    JSONObject world = new JSONObject();
    JSONObject player = new JSONObject();
    JSONObject playerRole = new JSONObject();

    public void serializePlayer(Player player) {
        this.player.put("Class", saveClass(player.getRole()));
        this.player.put("Level", player.getLevel());
        this.player.put("Location", saveLocation(player.getLocation()));
        this.player.put("Inventory", saveInventory(player.getInventory()));
        this.player.put("Equipment", saveEquipment(player.getEquipment()));
        this.player.put("HP", player.getHealth());
        this.player.put("EntityType", player.getEntityType());
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
        entityJSON.put("HP", entity.getHealth());
        entityJSON.put("MaxHP", entity.getMaxHealth());
        return entityJSON;
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
        locationJSON.put("AreaEffect", ""+location.getAreaEffect().getAreaEffectType() );
        locationJSON.put("Terrain", ""+location.getTerrain().getTerrainType());
        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 0; i < location.getItems().size(); i++){
            itemList.add("" + location.getItems().get(i));
        }
        locationJSON.put("Items", itemList);
        return locationJSON;
    }

    private JSONObject saveEntityLocation(EntityLocation entityLocation){
        //get entitylocationlist hashmap list or iterator
        JSONObject entityLocationJSON = new JSONObject();
        ArrayList<JSONObject> entityLocationList = new ArrayList<>();
        ArrayList<Pair> entityLocationPair = entityLocation.getAssociations();
        for (int i = 0; i < entityLocationPair.size(); i++){
            Entity entity = (Entity) entityLocationPair.get(i).getKey(); // I apoligize for this this needs to be changed to get keys/values the right way
            Location location = (Location) entityLocationPair.get(i).getValue();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Entity", saveEntity(entity));
            jsonObject.put("Location", saveLocation(location));
            entityLocationList.add(jsonObject);
        }
        entityLocationJSON.put("Pairs", new JSONArray(entityLocationList));
        return entityLocationJSON;
    }

    private JSONObject saveClass(Role role){
        JSONObject classJSON = new JSONObject();
        classJSON.put("BindWoundsLevel", role.getBindWounds());
        classJSON.put("BargainLevel", role.getBargain());
        classJSON.put("ObservationLevel", role.getObservation());
        role.save(this);
        classJSON.put("Role", playerRole);
        return classJSON;
    }


    private JSONObject saveInventory(Inventory inventory) {
        JSONObject inventoryJSON = new JSONObject();
        ArrayList<String> items = new ArrayList<>();
        for(int i = 0;inventory.getIterator().hasNext();i++, inventory.getIterator().next()){
            items.add(inventory.getIterator().getCurrent().save(this));
        }
        inventoryJSON.put("Items:", new JSONArray(items));
        return inventoryJSON;
    }

    private JSONObject saveEquipment(Equipment equipment) {
        JSONObject equipmentJSON = new JSONObject();
        equipment.getEquipped(0).save(this); //Todo: This needs to be changed
        return equipmentJSON;
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
        return "InteractiveItem";
    }

    @Override
    public String saveGoldOneShotItem(GoldOneShotItem goldOneShotItem) {
        return "GoldOneShotItem";
    }

    @Override
    public String saveHealingOneShotItem(HealingOneShotItem healingOneShotItem) {
        return "HealingOneShotItem";
    }

    @Override
    public String saveManaOneShotItem(ManaOneShotItem manaOneShotItem) {
        return "ManaOneShotItem";
    }

    @Override
    public String saveXPOneShotItem(XPOneShotItem xpOneShotItem) {
        return "XPOneShotItem";
    }

    @Override
    public String saveBody(Body body) {
        return "Body";
    }

    @Override
    public String saveHelmet(Helmet helmet) {
        return "Helmet";
    }

    @Override
    public String saveLeg(Leg leg) {
        return "Leg";
    }

    @Override
    public String saveRing(Ring ring) {
        return "Ring";
    }

    @Override
    public String saveAngulariceAttack(AngularIceAttack angularIceAttack) {
        return "AngularIceAttack";
    }

    @Override
    public String saveLinearIceAttack(LinearIceAttack linearIceAttack) {
        return "LinearIceAttack";
    }

    @Override
    public String saveRadialIceBomb(RadialIceBomb radialIceBomb) {
        return "RadialIceBomb";
    }

    @Override
    public String saveHeal(Heal heal) {
        return "Heal";
    }

    @Override
    public String saveIncreaseMaxHealth(IncreaseMaxHealth increaseMaxHealth) {
        return "IncreaseMaxHealth";
    }

    @Override
    public String saveIncreaseXP(IncreaseXP increaseXP) {
        return "IncreaseXP";
    }

    @Override
    public String saveBrassKnuckles(BrassKnuckles brassKnuckles) {
        return "BrassKnuckles";
    }

    @Override
    public String saveSpikedGloves(SpikedGloves spikedGloves) {
        return "SpikedGloves";
    }

    @Override
    public String saveSwordHands(SwordHands swordHands) {
        return "SwordHands";
    }

    @Override
    public String saveCharm(Charm charm) {
        return "Charm";
    }

    @Override
    public String saveInsomnia(Insomnia insomnia) {
        return "Insomnia";
    }

    @Override
    public String saveSeppuku(Seppuku seppuku) {
        return "Seppuku";
    }

    @Override
    public String saveBlueLightSaber(BlueLightsaber blueLightsaber) {
        return "BlueLightsaber";
    }

    @Override
    public String saveMjolnir(Mjolnir mjolnir) {
        return "Mjolnir";
    }

    @Override
    public String saveThunderBlade(ThunderBlade thunderBlade) {
        return "ThunderBlade";
    }

    @Override
    public String saveHealthPotion(HealthPotion healthPotion) {
        return "HealthPotion";
    }

    @Override
    public String saveManaPotion(ManaPotion manaPotion) {
        return "ManaPotion";
    }

    @Override
    public String saveXPPotion(XPPotion xpPotion) {
        return "XPPotion";
    }

    @Override
    public String saveAngularProjectile(AngularProjectile angularProjectile) {
        return "AngularProjectile";
    }

    @Override
    public String saveLinearProjectile(LinearProjectile linearProjectile) {
        return "LinearProjectile";
    }

    @Override
    public String saveRadialProjectile(RadialProjectile radialProjectile) {
        return "RadialProjectile";
    }

    @Override
    public String savePizza(Pizza pizza) {
        return "Pizza";
    }

    @Override
    public String saveSnowLauncher(SnowLauncher snowLauncher) {
        return "SnowLauncher";
    }

    @Override
    public String saveSnowShuriken(SnowShuriken snowShuriken) {
        return "SnowShuriken";
    }

    @Override
    public String saveStaff(Staff staff) {
        return "Staff";
    }

    @Override
    public String saveInquisitorLightsaber(InquisitorLightsaber inquisitorLightsaber) {
        return "InquisitorLightsaber";
    }

    @Override
    public String saveJeweledCutlass(JeweledCutlass jeweledCutlass) {
        return "JeweledCutlass";
    }

    @Override
    public String saveWaterHammer(WaterHammer waterHammer) {
        return "WaterHammer";
    }

    public JSONObject getWorld() {
        return world;
    }

    public JSONObject getPlayer() {
        return player;
    }
}
