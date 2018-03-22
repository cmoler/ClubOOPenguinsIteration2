package Model.Item.TakeableItem.BrawlingItem;

import Model.Entity.Equipment;
import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.Smasher;
import Model.Entity.Role.Summoner;
import Model.Item.TakeableItem.BaneItem.AngularIceAttack;
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

class BrassKnucklesTest {

    /*@Test
    public void testUse() {

        Map map = new Map(10,10);
        Location npc1Location = map.getDefaultLocation().getAdjacentAt(Direction.N);
        NPC npc1 = new NPC();
        map.setEntityLocation(npc1Location, npc1);
        World.getWorld().addMap("0001", map);
        World.getWorld().changeCurrentMapTo(map);

        Role role = new Smasher();
        Player player = new Player(role);
        map.setEntityLocation(map.getDefaultLocation(), player);
        player.addMana(100);
        player.move(Direction.N);

        TakeableItem item = new BrassKnuckles();

        Equipment equipment.xml = player.getEquipment();

        equipment.xml.equip(item);

        equipment.xml.useItem(0);
        double t = Time.currentInSeconds();
        while(Time.currentInSeconds() < t + 5) {
            equipment.xml.useItem(0);
        }
        assertEquals(65, npc1.getHealth(), "NPC got smacked with BrassKnuckles a bunch of times");

    }*/

}