package Controller.SavingLoading;

import Model.Entity.*;
import Model.Entity.Role.Role;
import Model.Map.Location;
import Model.Map.World;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameLoader {

    private World world;
    private Player player;

    public void loadGame(String filePath) throws FileNotFoundException {

        JSONObject jsonGameContent = loadGameContentJSON(filePath);
        loadPlayer(jsonGameContent);
        loadWorld(jsonGameContent);
    }

    private JSONObject loadGameContentJSON(String filePath) throws FileNotFoundException{

        try {
            File saveFile = new File(filePath);
            String saveFileContent = new Scanner(saveFile).useDelimiter("\\Z").next();

            JSONObject gameContentJSON = new JSONObject(saveFileContent);

            return  gameContentJSON;

        } catch (FileNotFoundException fileNotFoundException) {
            throw fileNotFoundException;
        }

    }

    private void loadWorld(JSONObject worldJSON){
        world = World.getWorld();


    }

    private void loadPlayer(JSONObject jsonGameContent){

        JSONObject playerJSON = jsonGameContent.getJSONObject("Player");

        Inventory inventory = loadInventory(playerJSON.getJSONObject("Inventory"));
        Equipment equipment = loadEquipment(playerJSON.getJSONObject("Equipment"));
        Location   location = loadLocation(playerJSON.getJSONObject("Location"));
        Role           role = loadRole(playerJSON.getJSONObject("Class"));

        int level = loadLevel(playerJSON);
        int HP    = loadHP(playerJSON);
        int maxHP = loadMaxHP(playerJSON);
        int mana  = loadMana(jsonGameContent);
        int XP    = loadXP(jsonGameContent);
        int gold  = loadGold(jsonGameContent);


        //this.player = new Player(role, )
    }

    private Role loadRole(JSONObject role){

    }



    private Location loadLocation(JSONObject location){

    }

    private Inventory loadInventory(JSONObject inventory){

    }

    private Equipment loadEquipment(JSONObject equipment){

    }

    private int loadLevel(JSONObject playerJSON){
        int level = playerJSON.getInt("Level");
    }

    private int loadHP(JSONObject playerJSON){
        int HP  = playerJSON.getInt("HP");
    }

    private int loadMaxHP(JSONObject playerJSON){
        int maxXP = playerJSON.getInt("MaxHP");
    }

    private int loadMana(JSONObject playerJSON){
        int mana = playerJSON.getInt("Mana");
    }

    private int loadXP(JSONObject playerJSON){
        int XP = playerJSON.getInt("XP");
    }

    private int loadGold(JSONObject playerJSON){
        int gold = playerJSON.getInt("Gold");
    }
}