package Model.Entity;

import Model.Entity.Role.Role;
import Model.Map.Direction;
import Model.Map.Location;
import View.Viewport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Entity{

    private List<Viewport> observers = new ArrayList<Viewport>();

    private Role role;
    private int maxHealth = 100;
    private int health = maxHealth;
    private int mana;
    private int gold;
    private Direction directionFacing;
    private int experience = 0;
    private int level = 1; // default level
    private Inventory inventory = new Inventory(this);
    private Equipment equipment = new Equipment(this);
    private EntityType entityType;
    private Location location;
    private boolean intentToMove = false;
    // map is in World

    public Entity(Location initialLocation) {
        entityType = EntityType.ICE; // default EntityType
        location = initialLocation;
    }

    public Entity(EntityType type, Location initialLocation){
        entityType = type;
        location = initialLocation;
    }

    public EntityType getEntityType(){
        return entityType;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void takeDamage(int damage){
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

    public void addGold(int gold){
        this.gold += gold;
    }

    public void addMana(int mana){
        this.mana += mana;
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

    public void setLocation(Location location){
        this.location = location;
    }

    public void teleport(Location location){
        this.location = location;
        notifyView();
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

    public int getGold() {
        return gold;
    }

    public int getMana() {
        return mana;
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

    public void attach(Viewport viewport){
        observers.add(viewport);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView() {
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