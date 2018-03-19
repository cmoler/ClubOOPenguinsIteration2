package Model.Entity.NPC;

import Model.Entity.Entity;
import Model.Entity.EntityType;
import Model.Entity.NPC.NPCState.*;

import java.util.Random;

import Model.Entity.Player;
import Model.Map.World;
import Model.UpdateList;
import Model.Updateable;
import Model.Entity.NPC.NPCState.AggroState;
import Model.Entity.NPC.NPCState.FriendlyState;
import Model.Entity.NPC.NPCState.NPCState;
import Model.Utilites.Time;

public class NPC extends Entity implements Updateable{

    private NPCState npcState;
    protected Player player;
    private boolean talking;
    private int talkTimer;
    private String talkString;
    private String color;

    private double secondsPerMove = 1;
    private double lastMove = Time.currentInSeconds();

    public NPC(String color, EntityType entityType) {
        super.setEntityType(entityType);
        this.color = color;
        name = "NPC";
        talkString = "Hello World!";
        UpdateList.getInstance().add(this);
    }

    public void talk(){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 80 ){
            //Display talkString
            System.out.println(talkString);
            talking = true;
            talkTimer = 10;
        }
        else{
            pissOff();
        }
    }

    public boolean isTalking() {
        return talking;
    }

    public String getTalkString() { return talkString; }

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
        if(Time.currentInSeconds() > lastMove + secondsPerMove) {
            npcState.move(this, player);
            lastMove = Time.currentInSeconds();
            if(talkTimer > 0)
                talkTimer--;
            else
                talking = false;
        }
    }

    @Override
    public boolean isDone() {
        return getHealth() <= 0;
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
            World.getWorld().getCurrentMap().removeEntityLocation(this.getLocation());
        }
        notifyView();

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

    public Player getPlayer() {
        return player;
    }

    public String getColor() {
        return color;
    }

    public String getState(){
        return npcState.getType();
    }
}
