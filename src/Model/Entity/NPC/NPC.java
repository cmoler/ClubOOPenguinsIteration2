package Model.Entity.NPC;

import Model.Entity.Entity;
import Model.Entity.NPC.NPCState.*;

import java.util.Random;
import Model.Updateable;

public class NPC extends Entity implements Updateable{



    private NPCState npcState;
    private boolean wantToTalk;
    private String talkString;

    public NPC() {

    }

    public void talk(){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 80 ){
            //Display talkString
            System.out.println(talkString);
        }
        else{
            pissOff();
        }
    }

    public void beFriends(){
        NPCState friendlyState = new FriendlyState();
        setNpcState(friendlyState);
    }

    public void pissOff(){
        NPCState aggroState = new AggroState();
        setNpcState(aggroState);
    }

    public void fallAsleep(){
        // makes NPC sleep for certain period of time
        NPCState sleepState = new SleepState(this, npcState, 10);
        setNpcState(sleepState);
    }

    public void setNpcState(NPCState npcState) {
        this.npcState = npcState;
    }

    @Override
    public void update(){
        npcState.move();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
