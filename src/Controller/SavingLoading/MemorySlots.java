package Controller.SavingLoading;

import Configs.Commons;

import java.util.ArrayList;
import java.util.List;

public class MemorySlots {
    List<Slot> slots = new ArrayList<>();
    GameBuilder gameBuilder;
    int selectedSlot;
    final static int SMASHER = Commons.MAX_SAVE_SLOTS + 1;
    final static int SUMMONER = Commons.MAX_SAVE_SLOTS + 2;
    final static int SNEAK= Commons.MAX_SAVE_SLOTS + 3;
    public MemorySlots(GameBuilder gameBuilder) {
        selectedSlot = 0;
        this.gameBuilder = gameBuilder;

        //createslots
        for (int i = 0; i < Commons.MAX_SAVE_SLOTS; i++) {
            slots.add(new Slot(Commons.SAVE_FOLDER + i + ".json"));
        }
        slots.add(new Slot("smasher.json")); //5
        slots.add(new Slot("summoner.json")); //6
        slots.add(new Slot("sneak.json")); //7
    }

    public void selectPrevious(){
        selectedSlot--;
        if(selectedSlot < 0) selectedSlot = Commons.MAX_SAVE_SLOTS - 1;
    }


    public void selectNext(){
        selectedSlot++;
        if(selectedSlot > Commons.MAX_SAVE_SLOTS - 1) selectedSlot = 0;
    }

    public int getSelectedSlot(){
        return selectedSlot;
    }

    public void loadOnSelected(){
        slots.get(selectedSlot).loadTo(gameBuilder);
    }

    public void loadDefaultGameSmasher() {slots.get(SMASHER).loadTo(gameBuilder);}

    public void loadDefaultGameSummoner() {slots.get(SUMMONER).loadTo(gameBuilder);}

    public void loadDefaultGameSneak() {slots.get(SNEAK).loadTo(gameBuilder);}

    public void saveOnSelected(){
        slots.get(selectedSlot).saveFrom(gameBuilder);
    }
}
