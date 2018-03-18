package Model.Entity.NPC;

import Model.Entity.Entity;
import Model.Entity.NPC.NPCState.*;

import java.util.Random;

import Model.Entity.Player;
import Model.Updateable;
import Model.Entity.NPC.NPCState.AggroState;
import Model.Entity.NPC.NPCState.FriendlyState;
import Model.Entity.NPC.NPCState.NPCState;

public class NPC extends Entity implements Updateable{

    private NPCState npcState;
    protected Player player;
    private boolean wantToTalk;
    private String talkString;
    private String color;

    public NPC(String color) {
        this.color = color;
        name = "NPC";
        talkString = "Hello World!";
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
        NPCState sleepState = new SleepState(npcState, 10);
        setNpcState(sleepState);
    }

    public void setNpcState(NPCState npcState) {
        this.npcState = npcState;
    }

    public int getVisibleRange() {
        return player.getVisibleRange();
    }

    @Override
    public void update(){
        npcState.move(this, player );
    }

    @Override
    public boolean isDone() {
        return false;
    }


    //OVERRIDED ENTITY METHODS

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (isAlive()) {
            pissOff();
        }
        else {
            player.modifyGold(100);
            player.gainExperience(50);
        }

    }

    @Override
    public void interactEntity(Entity entity) {
        if (player == entity){
            player.takeDamage(10);
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getColor() {
        return color;
    }

    public String getState(){
        return npcState.getType();
    }
}
