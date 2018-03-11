package Controller.SavingLoading;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Skill.Skill;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class GameSaver implements Saver{


    @Override
    public String saveEntity(Entity entity) {
        return entity.toString();
    }

    @Override
    public String saveLocation(Location location) {
        return location.toString();
    }

    @Override
    public String saveMap(Map map) {
        return map.toString();
    }

    @Override
    public String saveWorld(World world) {
        return world.toString();
    }

    @Override
    public String saveInventory(Inventory inventory) {
        return inventory.toString();
    }

    @Override
    public String saveSkill(Skill skill) {
        return skill.toString();
    }

    @Override
    public String saveEquipment(Equipment equipment) {
        return equipment.toString();
    }

}
