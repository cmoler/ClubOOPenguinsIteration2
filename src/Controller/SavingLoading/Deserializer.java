package Controller.SavingLoading;

import Model.Entity.*;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Sneak;
import Model.Entity.Role.Summoner;
import Model.Entity.Skill.*;
import Model.Map.AreaEffect.*;
import Model.Map.Location;
import Model.Map.Terrain.Terrain;
import Model.Map.World;
import org.json.JSONObject;

import java.awt.geom.Area;

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

    }

    private Equipment deserializeEquipment(JSONObject equipment){

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