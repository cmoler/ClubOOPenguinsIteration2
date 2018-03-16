package Model.Entity;

import Model.Entity.Role.Role;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Visitor.Visitor;
import View.Viewport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class Entity {

    private List<Viewport> observers = new ArrayList<Viewport>();


    private EntityType entityType;
    private int maxHealth = 100;
    private int health = maxHealth;
    private Direction directionFacing;
    private int experience = 0;
    private int level = 1; // default level
    private double defense = 0;
    private Inventory inventory = new Inventory(this);
    private Location location;
    private boolean intentToMove = false;
    // map is in World


    public EntityType getEntityType(){
        return entityType;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void takeDamage(int damage){
        if(defense > 0)
            damage = (int)(((double)damage) * defense/100);
        health -= damage;
        if (health < 0)
            health = 0;
        notifyView();
    }

    public void heal(int healing){
        health += healing;
        if (health > maxHealth)
            health = maxHealth;
        notifyView();
    }

    public void gainExperience(int experience){
        this.experience += experience;
        while (canLevelUp())
            level++;
        notifyView();
    }

    public void modifyMaxHealth(int health){
        maxHealth += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    public void modifyDefense(double defense){
        this.defense = defense;
    }

    private boolean canLevelUp(){
        if (level < finalLevel) {
            if (experience > ExperienceForLevel.get(level + 1))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public void move(Direction direction){

        if (directionFacing == direction){
            intentToMove = true;
        }
        else {
            directionFacing = direction;

        }
    }

    public boolean getIntentToMove(){
        return intentToMove;
    }

    public void setIntentToMove(boolean intentToMove) {
        this.intentToMove = intentToMove;
    }

    public Direction getDirectionFacing() {
        return directionFacing;
    }


    // getters
    public int getHealth(){
        return health;
    }

    public int getMaxHealth() { return maxHealth; }

    public int getExperience(){
        return experience;
    }

    public int getLevel(){
        return level;
    }


    public int getExperienceForNextLevel(){
        if(level < finalLevel)
            return ExperienceForLevel.get(level + 1);
        else return -1;
    }

    public int getExperienceForCurrentLevel(){
        return ExperienceForLevel.get(level);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Location getLocation() {
        return location;
    }

    public void interactLocation(){
        location.activateAreaEffect(this);
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void accept(Visitor v){
        v.visitEntity(this);
    }

    public void attach(Viewport viewport){
        observers.add(viewport);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView(){
        for (Viewport viewport : observers){
            viewport.update();
        }
    }
  
    private static final int finalLevel = 100;
    private static final java.util.Map<Integer, Integer> ExperienceForLevel; // <Level,Experience required for level>
    static {
        java.util.Map<Integer, Integer> modifiableMap = new HashMap<Integer, Integer>();

        int experienceInterval = 100; // if every level requires the same experience interval
        int experienceNeeded = 0;
        for(int level=1; level<=finalLevel; level++) {
            modifiableMap.put(level, experienceNeeded);
            experienceNeeded += experienceInterval;
        }

        ExperienceForLevel = Collections.unmodifiableMap(modifiableMap);
    }

}