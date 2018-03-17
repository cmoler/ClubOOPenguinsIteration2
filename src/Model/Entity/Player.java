package Model.Entity;

import Model.Entity.NPC.NPC;
import Model.Entity.Role.Role;
import Model.Map.Location;

public class Player extends Entity {
    private Role role;
    private int mana;
    private int gold;
    private Equipment equipment = new Equipment(this);

    public Player(Role role) {
        this.role = role;
        role.setEntity(this);
        super.setEntityType(EntityType.ICE);// default EntityType
    }

    public Player(Role role, EntityType type){
        this.role = role;
        role.setEntity(this);
        super.setEntityType(type);
    }

    public void touchItems(){
        getLocation().itemsTouchedBy(this);
    }

    public void useRoleTraits(){
        role.activateTrait(super.getLocation());
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    @Override
    public void interactLocation() {
        super.interactLocation();
        touchItems();
        useRoleTraits();
    }

    @Override
    public void interactEntity(Entity entity) {
        ((NPC) entity).talk();
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
