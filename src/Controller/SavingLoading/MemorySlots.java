package Controller.SavingLoading;

import Configs.Commons;

import java.util.List;

public class MemorySlots {
    List<Slot> slots;
    GameLoader gameLoader;
    int selectedSlot;

    public MemorySlots(GameLoader gameLoader) {
        selectedSlot = 0;
        this.gameLoader = gameLoader;

        //createslots
        for (int i = 0; i < Commons.maxSaveSlots; i++) {
            slots.add(new Slot(Commons.saveFolder + Commons.saveName + i));
        }
    }

    public void selectPrevious(){
        selectedSlot--;
        if(selectedSlot < 0) selectedSlot = Commons.maxSaveSlots - 1;
    }


    public void selectNext(){
        selectedSlot++;
        if(selectedSlot > Commons.maxSaveSlots - 1) selectedSlot = 0;
    }

    public void loadOnSelected(){
        slots.get(selectedSlot).loadTo(gameLoader);
    }

    public void saveOnSelected(){
        slots.get(selectedSlot).saveFrom(gameLoader);
    }
}
