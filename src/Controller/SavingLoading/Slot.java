package Controller.SavingLoading;


import java.io.*;

/*
Purpose of this class is to load and save a game from it specified location.
 */
public class Slot {

    File file;
    int gameTime;
    String classtype;

    public Slot(String fileName){
        file = new File(fileName);


        //must be pulled from file (must also be saved to file), is needed here for the view to show playtime under each slot.
        gameTime = 0;
    }

    public void saveFrom(GameLoader gameLoader){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameSaver saver;
//
//        for each model in gameloader{
//            file.write(model.save(saver))
//        }

    }

    public void loadTo(GameLoader gameLoader){
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public int getGameTime() {
        return gameTime;
    }
}
