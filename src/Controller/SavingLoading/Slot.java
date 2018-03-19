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
        System.out.println("saving... to : " + savePath);
        System.exit(0);
    }

    public void loadTo(GameBuilder gameBuilder){
        GameLoader gameLoader = new GameLoader(gameBuilder);
        gameLoader.load(savePath);
    }

    public int getGameTime() {
        return gameTime;
    }
}
