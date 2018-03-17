package Controller.SavingLoading;

import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Skill.Bargain;
import Model.Entity.Skill.BindWounds;
import Model.Entity.Skill.Observation;
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
        Role           role = deserializeRole(playerJSON.getJSONObject("Role"));

        int level = deserializeLevel(playerJSON);
        int HP    = deserializeHP(playerJSON);
        int maxHP = deserializeMaxHP(playerJSON);
        int mana  = deserializeMana(saveFileJSON);
        int XP    = deserializeXP(saveFileJSON);
        int gold  = deserializeGold(saveFileJSON);

        return null;
    }

    private Role deserializeRole(JSONObject playerClass){

        BindWounds bindWounds = new BindWounds(playerClass.getInt("BindWoundsLevel"));
        Bargain bargain = new Bargain(playerClass.getInt("BargainLevel"));
        //Observation observation = new Observation(playerClass.getInt("ObservationLevel"));

        //JSONObject roleJSON = playerClass.getJSONObject("SpecificRole");

        /*
        private JSONObject saveClass(Role role){
            JSONObject classJSON = new JSONObject();
            classJSON.put("BindWoundsLevel", role.getBindWounds());
            classJSON.put("BargainLevel", role.getBargain());
            classJSON.put("ObservationLevel", role.getObservation());
            role.save(this);
            classJSON.put("Role", playerRole);
            return classJSON;
        }
        */

        return null;
    }

    private Location deserializeLocation(JSONObject location){
        return null;

    }

    private Inventory deserializeInventory(JSONObject inventory){
        return null;

    }

    private Equipment deserializeEquipment(JSONObject equipment){
        return null;

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