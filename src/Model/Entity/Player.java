package Model.Entity;

import Model.Entity.Role.Role;

public class Player extends Entity {
    private Role role;
    private int mana;
    private int gold;
    private Equipment equipment = new Equipment(this);

    public Player(Role role) {
        this.role = role;
        super.setEntityType(EntityType.ICE);// default EntityType
    }

    public Player(Role role, EntityType type){
        this.role = role;
        super.setEntityType(type);
    }

    public void touchItems(){
        super.getLocation().itemsTouchedBy(this);
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

}
