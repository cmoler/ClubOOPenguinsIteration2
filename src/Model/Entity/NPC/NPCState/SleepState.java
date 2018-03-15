package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;

public class SleepState implements NPCState {

    private NPCState oldState;
    private NPC npc;
    private int duration;
    private long startTime;

    public SleepState(NPC npc, NPCState oldState, int duration){
        this.oldState = oldState;
        this.npc = npc;
        this.duration = duration;
        startTime = System.nanoTime();
    }

    @Override
    public void move(NPC npc, Player player) {
        if ( (int) (System.nanoTime() - startTime)/1000000000 >= duration){
            this.npc.setNpcState(oldState);
        }
    }


}

