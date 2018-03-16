package Controller.SavingLoading;

import Model.Entity.Entity;
import Model.Map.World;
import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameLoader {

    private World world;
    private Entity playerEntity;

    public void loadGameContentJSON(String filePath){



        try {
            File saveFile = new File(filePath);
            String saveFileContent = new Scanner(saveFile).useDelimiter("\\Z").next();

            JSONObject gameContentJSON = new JSONObject(saveFileContent);
            JSONObject worldJSON = gameContentJSON.getJSONObject("World");
            JSONObject playerEntityJSON = gameContentJSON.getJSONObject("PlayerEntity");

            loadWorld(worldJSON);
            loadPlayerEntity(playerEntityJSON);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void loadWorld(JSONObject worldJSON){
        world = World.getWorld();



    }

    private void loadPlayerEntity(JSONObject playerEntityJSON){
        playerEntity

    }
}