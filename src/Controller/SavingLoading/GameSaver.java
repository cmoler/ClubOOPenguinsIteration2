package Controller.SavingLoading;

import Model.Entity.Player;
import Model.EntityLocation;
import org.json.*;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Skill.Skill;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameSaver implements Saver{
    JSONObject world = new JSONObject();
    JSONObject player = new JSONObject();

    public void savePlayer(Player entity) {
    }

    private JSONObject saveEntity(Entity entity){
        JSONObject entityJSON = new JSONObject();
        entity.put("avatar", entity.getAvatar)
    }

    private JSONObject saveLocation(Location location) {
        JSONObject locationJSON = new JSONObject();
        locationJSON.put("AreaEffect", ""+location.getAreaEffect().getAreaEffectType() );
        locationJSON.put("Terrain", ""+location.getTerrain().getTerrainType());
        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 0; i < location.getItems().size(); i++){
            itemList.add("" + location.getItems().get(i).getItemType());
        }
        locationJSON.put("Items", itemList);
        return locationJSON;
    }

    private JSONObject saveMap(Map map) {
        JSONObject mapJSON = new JSONObject();
        mapJSON.put("rows", map.getRows());
        mapJSON.put("cols", map.getCols());
        ArrayList<JSONObject> entityLocationList = new ArrayList<>();
        for (int i = 0; i < map..size(); i++)
                entityLocationList.get(i).
        }
        mapJSON.put("")
    }

    
    public void saveWorld(World world) {
    }

    private JSONObject saveEntityLocation(EntityLocation entityLocation){
        JSONObject entityLocationJSON = new JSONObject();
        entityLocationJSON.put("Entity", saveEntity(entityLocation.getEntity()));
        entityLocationJSON.put("Location", saveLocation(entityLocation.getLocation());
        return entityLocationJSON;
    }

    
    private JSONObject saveInventory(Inventory inventory) {
        JSONObject inventoryJSON = new JSONObject();
    }

    
    private JSONObject saveSkill(Skill skill) {
        JSONObject skillJSON = new JSONObject();
    }

    
    private JSONObject saveEquipment(Equipment equipment) {
        JSONObject equipmentJSON = new JSONObject();
    }

    public JSONObject getWorld() {
        return world;
    }

    public JSONObject getPlayer() {
        return player;
    }
}
