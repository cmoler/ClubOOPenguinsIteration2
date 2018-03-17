package Controller.SavingLoading;
import java.io.*;
import org.json.*;
/*
Purpose of this class is to load and save a game from it specified location.
 */
public class Slot {

    private String savePath;
    private int gameTime;
    private String classtype;

    public Slot(String savePath){
        this.savePath = savePath;
        //must be pulled from file (must also be saved to file), is needed here for the view to show playtime under each slot.
        gameTime = 0;
    }

    public void saveFrom(GameBuilder gameBuilder){
        GameSaver saver = new GameSaver(gameBuilder);
        saver.save(savePath);
    }

    public void loadTo(GameBuilder gameBuilder){
        FileReader fileReader;
        String source;
        try {
            fileReader = new FileReader(new File(savePath));
            source = ""+ fileReader.read(); //todo: read files correctly this isnt correct

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int getGameTime() {
        return gameTime;
    }
}
