package Model.Entity;

import Model.Entity.Role.Role;
import Model.Map.Location;

public class Player extends Entity {
    private Role role;
    private int mana;
    private int gold;
    private Equipment equipment = new Equipment(this);
    private Location location;

    public Player(Role role, Location initialLocation) {
        this.role = role;
        role.setEntity(this);
        this.location = initialLocation;
        super.setEntityType(EntityType.ICE);// default EntityType
    }

    public Player(Role role, Location initialLocation, EntityType type){
        this.role = role;
        role.setEntity(this);
        super.setEntityType(type);
    }

    public void touchItems(){
        super.getLocation().itemsTouchedBy(this);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public Role getRole(){
        return role;
    }

    public int getMana() {
        return mana;
    }

    public void addMana(int mana){
        this.mana += mana;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold){
        this.gold += gold;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
