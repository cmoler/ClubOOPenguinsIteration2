package Controller.SavingLoading;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Skill.Skill;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;

public class GameSaveVisitor implements Saver{


    @Override
    public String saveEntity(Entity entity) {
        String source= "";
        source += entity.getHealth();

        //...
        return entity.toString();
    }

    @Override
    public String saveLocation(Location location) {
        String source = "";
//        source += "AE: " + location.getAreaEffect().getAreaEffectType() + "\t";
//        source += "TT: " + location.getTerrain().getTerrainType() + "\t";
//        source += "II: ";
//        for(int i = 0; i < location.getItems().size(); i++) {
//             source += location.getItems().get(i).getItemType() + "\t";
//        }



        return source;
    }

    @Override
    public String saveMap(Map map) {
        String source = "";
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
        return source;
    }

    @Override
    public String saveWorld(World world) {
        String source = "";
        source += saveMap(world.getCurrentMap());
        return source;
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
