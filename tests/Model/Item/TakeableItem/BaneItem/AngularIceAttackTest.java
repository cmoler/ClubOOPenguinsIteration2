package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Equipment;
import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Summoner;
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

class AngularIceAttackTest {

    @Test
    public void testUse() {

        Map map = new Map(10,10);
        Location npc1Location = map.getDefaultLocation().getAdjacentAt(Direction.SW).getAdjacentAt(Direction.SW).getAdjacentAt(Direction.SW);
        Location npc2Location = map.getDefaultLocation().getAdjacentAt(Direction.SW).getAdjacentAt(Direction.W);
        Location npc3Location = map.getDefaultLocation().getAdjacentAt(Direction.NW).getAdjacentAt(Direction.NW).getAdjacentAt(Direction.NW);
        Location npc4Location = map.getDefaultLocation().getAdjacentAt(Direction.S).getAdjacentAt(Direction.S).getAdjacentAt(Direction.S);
        NPC npc1 = new NPC();
        NPC npc2 = new NPC();
        NPC npc3 = new NPC();
        NPC npc4 = new NPC();
        map.setEntityLocation(npc1Location, npc1);
        map.setEntityLocation(npc2Location, npc2);
        map.setEntityLocation(npc3Location, npc3);
        map.setEntityLocation(npc4Location, npc4);
        World.getWorld().addMap("0001", map, new MapView());
        World.getWorld().changeCurrentMapTo(map);

        Role role = new Summoner();
        Player player = new Player(role, map.getDefaultLocation());
        player.addMana(100);
        player.move(Direction.W);

        TakeableItem item = new AngularIceAttack();

        Equipment equipment = player.getEquipment();

        equipment.equip(item);

        equipment.useItem(0);
//        int x = ((ProjectileCapableItem)item).getProjectiles().get(0).getLocationsOn().get(0).getxCoordinate();
//        int y = ((ProjectileCapableItem)item).getProjectiles().get(0).getLocationsOn().get(0).getyCoordinate();
//        System.out.println("x: " + x + ", y: " + y);
        double t = Time.currentInSeconds();
        while(Time.currentInSeconds() < t + 5) {
            UpdateList.getInstance().update();
        }
        assertEquals(95, npc1.getHealth(), "NPC hit with AngularIceAttack");
        assertEquals(90, npc2.getHealth(), "NPC hit with AngularIceAttack");
        assertEquals(95, npc3.getHealth(), "NPC hit with AngularIceAttack");
        assertEquals(100, npc4.getHealth(), "NPC not hit");
    }
}