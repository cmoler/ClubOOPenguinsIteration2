package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;

public class SleepState implements NPCState {

    private NPCState oldState;
    private int duration;
    private long startTime;

    public SleepState(NPCState oldState, int duration){
        this.oldState = oldState;
        this.duration = duration;
        startTime = System.nanoTime();
    }

    @Override
    public void move(NPC npc, Player player) {
        if ( (int) (System.nanoTime() - startTime)/1000000000 >= duration){
            npc.setNpcState(oldState);
        }
    }


}

