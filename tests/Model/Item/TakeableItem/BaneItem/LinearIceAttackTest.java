package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Entity.Equipment;
import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.UpdateList;
import Model.Utilites.Time;
import View.AreaView.MapView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearIceAttackTest {

    /*@Test
    public void testUse(){

        Map map = new Map(10,10);
        Location NPCLocation = map.getDefaultLocation().getAdjacentAt(Direction.S).getAdjacentAt(Direction.S).getAdjacentAt(Direction.S);
        NPC npc = new NPC();
        map.setEntityLocation(NPCLocation, npc);
        World.getWorld().addMap("0001", map);
        World.getWorld().changeCurrentMapTo(map);

        Role role = new Summoner();
        Player player = new Player(role);
        map.setEntityLocation(map.getDefaultLocation(), player);
        player.addMana(100);
        player.move(Direction.S);

        TakeableItem item = new LinearIceAttack();

        Equipment equipment.xml = player.getEquipment();

        equipment.xml.equip(item);

        equipment.xml.useItem(0);
//        int x = ((ProjectileCapableItem)item).getProjectiles().get(0).getLocationsOn().get(0).getxCoordinate();
//        int y = ((ProjectileCapableItem)item).getProjectiles().get(0).getLocationsOn().get(0).getyCoordinate();
//        System.out.println("x: " + x + ", y: " + y);
        double t = Time.currentInSeconds();
        while(Time.currentInSeconds() < t + 5) {
            UpdateList.getInstance().update();
        }
        assertEquals(90, npc.getHealth(), "NPC hit with a LinearIceAttack");

    }*/

}