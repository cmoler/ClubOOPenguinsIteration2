package Controller.SavingLoading;

import org.json.*;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Skill.Skill;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class GameSaveVisitor implements Saver{
    JSONObject world = new JSONObject();
    JSONObject player = new JSONObject();

    public void savePlayer(Entity entity) {

    }

    private void saveLocation(Location location) {
        JSONObject locationJSON = new JSONObject();
        locationJSON.put("AreaEffect", location.getAreaEffect() );
        locationJSON.put("Terrain", location.getTerrain());
        locationJSON.put("Items", location.getItems());
//        source += "AE: " + location.getAreaEffect().getAreaEffectType() + "\t";
//        source += "TT: " + location.getTerrain().getTerrainType() + "\t";
//        source += "II: ";
//        for(int i = 0; i < location.getItems().size(); i++) {
//             source += location.getItems().get(i).getItemType() + "\t";
//        }
    }

    private void saveMap(Map map) {
        JSONObject mapJSON = new JSONObject();
//        int ilength = map.getCols();
//        int jlength = map.getRows();
//        source += "CL: " + ilength + "\n";
//        source += "RS: " + jlength + "\n";
//        for(int i = 0; i < ilength; i++){
//            source += "\n";
//            for(int j = 0; j < jlength; j++){
//                source += ": " + saveLocation(map.getLocationIJ(i,j)) + "\t";
//            }
//        }
    }

    
    public void saveWorld(World world) {
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
