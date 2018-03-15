package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;

import java.util.Timer;

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
    public void move() {
        if ( (int) (System.nanoTime() - startTime)/1000000000 >= duration){
            npc.setNpcState(oldState);
        }
    }


}

