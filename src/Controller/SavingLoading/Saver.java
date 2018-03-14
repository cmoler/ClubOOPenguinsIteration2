package Controller.SavingLoading;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Skill.Skill;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public interface Saver {

    public String saveEntity(Entity entity);

    public String saveLocation(Location location);

    public String saveMap(Map map);

    public String saveWorld(World world);

    public String saveInventory(Inventory inventory);
    
    public String saveSkill(Skill skill);
    
    public String saveEquipment(Equipment equipment);

}
