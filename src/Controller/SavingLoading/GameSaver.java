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
        gameBuilder.getWorld().save(serializer);
        saveJSON.put("World", serializer.getWorld());
        System.out.println(saveJSON.toString());

        try {
            FileWriter fileWriter = new FileWriter(new File(filepath));
            fileWriter.write(saveJSON.toString(1));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
