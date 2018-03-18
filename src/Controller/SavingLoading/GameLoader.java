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

    private GameBuilder gameBuilder;

    public GameLoader(GameBuilder gameBuilder){
        this.gameBuilder = gameBuilder;
    }

    public void load(String savePath){
        File file = new File(savePath);

        String saveFileContent = null;
        try {
            saveFileContent = new Scanner(file).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject saveFileJSON = new JSONObject(saveFileContent);

        Deserializer deserializer = new Deserializer(gameBuilder, saveFileJSON);
        
    }
}