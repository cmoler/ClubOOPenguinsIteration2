package Controller.SavingLoading;

import Configs.Commons;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemorySlots {
    List<Slot> slots = new ArrayList<>();
    GameLoader gameLoader;
    int selectedSlot;

    public MemorySlots(GameLoader gameLoader) {
        selectedSlot = 0;
        this.gameLoader = gameLoader;

        //createslots
        for (int i = 0; i < Commons.MAX_SAVE_SLOTS; i++) {
            slots.add(new Slot(Commons.SAVE_FOLDER + Commons.SAVE_NAME + i));
        }
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
        slots.get(selectedSlot).loadTo(gameLoader);
    }

    public void saveOnSelected(){
        slots.get(selectedSlot).saveFrom(gameLoader);
    }
}
