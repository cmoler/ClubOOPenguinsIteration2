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

    private Deserializer deserializer;

    private World world = World.getWorld();
    private Player player;

    public GameLoader(String filePath) throws FileNotFoundException{
        try{

            deserializer = new Deserializer(load(filePath));

            //world is a singleton, so deserializer can access world
            deserializer.deserializeWorld();

            //deserialize gives back a Player object
            player = deserializer.deserializePlayer();

        } catch (FileNotFoundException fileNotFoundException){
            throw fileNotFoundException;
        }
    }

    public Player getPlayer(){
        return player;
    }

    private JSONObject load(String filePath) throws FileNotFoundException {
        try {

            File saveFile = new File(filePath);
            String saveFileContent = new Scanner(saveFile).useDelimiter("\\Z").next();
            JSONObject saveFileJSON = new JSONObject(saveFileContent);

            return  saveFileJSON;

        } catch (FileNotFoundException fileNotFoundException) {
            throw fileNotFoundException;
        }
    }
}