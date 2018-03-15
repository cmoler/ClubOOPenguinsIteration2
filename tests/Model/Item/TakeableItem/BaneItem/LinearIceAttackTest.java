package Model.Item.TakeableItem.BaneItem;

import Model.Entity.Entity;
import Model.Entity.Role.Summoner;
import Model.Map.Map;
import Model.Map.World;
import View.AreaView.MapView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearIceAttackTest {

    @Test
    public void testUse(){
        Map map = new Map(10,10);
        World.getWorld().addMap("0001", map, new MapView());
        Entity entity;
    }

}