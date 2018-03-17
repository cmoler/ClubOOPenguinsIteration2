package Controller.SavingLoading;

import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Map.Location;
import Model.Map.World;
import org.json.JSONObject;

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

        Inventory inventory = deserializeInventory(playerJSON.getJSONObject("Inventory"));
        Equipment equipment = deserializeEquipment(playerJSON.getJSONObject("Equipment"));
        Location   location = deserializeLocation(playerJSON.getJSONObject("Location"));
        Role           role = deserializeRole(playerJSON.getJSONObject("Class"));

        int level = deserializeLevel(playerJSON);
        int HP    = deserializeHP(playerJSON);
        int maxHP = deserializeMaxHP(playerJSON);
        int mana  = deserializeMana(saveFileJSON);
        int XP    = deserializeXP(saveFileJSON);
        int gold  = deserializeGold(saveFileJSON);
    }

    private Role deserializeRole(JSONObject role){

    }

    private Location deserializeLocation(JSONObject location){

    }

    private Inventory deserializeInventory(JSONObject inventory){

    }

    private Equipment deserializeEquipment(JSONObject equipment){

    }

    private int deserializeLevel(JSONObject playerJSON){
        int level = playerJSON.getInt("Level");
    }

    private int deserializeHP(JSONObject playerJSON){
        int HP = playerJSON.getInt("HP");
    }

    private int deserializeMaxHP(JSONObject playerJSON){
        int maxXP = playerJSON.getInt("MaxHP");
    }

    private int deserializeMana(JSONObject playerJSON){
        int mana = playerJSON.getInt("Mana");
    }

    private int deserializeXP(JSONObject playerJSON){
        int XP = playerJSON.getInt("XP");
    }

    private int deserializeGold(JSONObject playerJSON){
        int gold = playerJSON.getInt("Gold");
    }

}