package Controller.SavingLoading;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver {
    GameBuilder gameBuilder;
    GameSaver(GameBuilder gameBuilder){
        this.gameBuilder = gameBuilder;
    }

    public void save(String filepath){
        JSONObject saveJSON = new JSONObject();
        Serializer serializer = new Serializer();
        //gameBuilder.getPlayer().save(serializer);
        gameBuilder.getWorld().save(serializer);
        //saveJSON.put("Player", serializer.getPlayer());
        saveJSON.put("World", serializer.getWorld());

        try {
            FileWriter fileWriter = new FileWriter(new File(filepath));
            fileWriter.write(saveJSON.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
