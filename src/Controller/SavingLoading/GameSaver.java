package Controller.SavingLoading;

import Model.Entity.Player;
import Model.EntityLocation;
import javafx.util.Pair;
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

    public void savePlayer(Player player) {
        JSONObject playerJSON = new JSONObject();
//        playerJSON.put("Class", player.getRole() );//todo: fix this
        playerJSON.put("Level", player.getLevel());
        playerJSON.put("Skills", "1 1 1 1");//Todo: fix this
//        playerJSON.put("X", player.getX());//Todo: fix this
//        playerJSON.put("Y", player.getY());//Todo: fix this
        playerJSON.put("Inventory", saveInventory(player.getInventory()));
//        playerJSON.put("Equipment", saveEquipment(player.getEquipment()));//Todo: fix this
        playerJSON.put("HP", player.getHealth());
        playerJSON.put("MaxHP", player.getMaxHealth());
//        playerJSON.put("Mana", player.getMana());//Todo: fix this
        playerJSON.put("XP", player.getExperience());
//        playerJSON.put("Gold", player.getGold());//Todo: fix this
    }

    private JSONObject saveEntity(Entity entity){
        JSONObject entityJSON = new JSONObject();
        entityJSON.put("Level", entity.getLevel());
//        entityJSON.put("X", entity.getX());//Todo: fix this
//        entityJSON.put("Y", entity.getY());//Todo: fix this
        entityJSON.put("Inventory", saveInventory(entity.getInventory()));
//        entityJSON.put("Equipment", saveEquipment(entity.getEquipment()));//Todo: fix this
        entityJSON.put("HP", entity.getHealth());
        entityJSON.put("MaxHP", entity.getMaxHealth());
        return entityJSON;
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
//        mapJSON.put("EntityLocations:", saveEntityLocation(map.getEntityLocations()));//Todo: fix this
//        ArrayList< ArrayList<Location> > locationMap = map.getMap();//Todo: fix this
        ArrayList< ArrayList<JSONObject> > locationMapJSON = new ArrayList< ArrayList<JSONObject> >();
//        for(int i = 0; i < locationMap.size(); i++ ){
//            for (int j = 0; j < locationMap.get(i).size(); j++){
//                locationMapJSON.get(i).set(j, saveLocation(locationMap.get(i).get(j)));
//            }
//        }
//        mapJSON.put("Locations", locationMapJSON); //todo: I don't think this needs to be 2 dimensional
        return mapJSON;
    }

    
    public void saveWorld(World world) {

    }

    private JSONObject saveEntityLocation(EntityLocation entityLocation){
        //get entitylocationlist hashmap list or iterator
        JSONObject entityLocationJSON = new JSONObject();
        ArrayList<JSONObject> entityLocationList = new ArrayList<>();
//        ArrayList<Pair> entityLocationPair = map.getEntityLocation.getList(); //Todo: fix this
//        for (int i = 0; i < entityLocationPair.size(); i++){
//            Entity entity = entityLocationPair.get(i).getKey();
//            Location location = entityLocationPair.get(i).getValue();
//        }
        return entityLocationJSON;
    }

    
    private JSONObject saveInventory(Inventory inventory) {
        JSONObject inventoryJSON = new JSONObject();
        ArrayList<String> items = new ArrayList<>();
        for(int i = 0;inventory.getIterator().hasNext();i++, inventory.getIterator().next()){
            items.add(""+inventory.getIterator().getCurrent().getItemType());//todo: getitemtype needs to be reliable
        }
        return inventoryJSON;
    }

    
    private JSONObject saveSkill(Skill skill) {
        JSONObject skillJSON = new JSONObject();
        return skillJSON;
    }

    
    private JSONObject saveEquipment(Equipment equipment) {
        JSONObject equipmentJSON = new JSONObject();
        return equipmentJSON;
    }

    public JSONObject getWorld() {
        return world;
    }

    public JSONObject getPlayer() {
        return player;
    }
}
