package Model.Entity.Skill;

import Model.Entity.Entity;
import Model.Entity.Inventory;
import Model.Map.Location;
import Model.Map.World;

public class PickPocket extends Skill {

    public PickPocket(){
        super();
    }

    public PickPocket(int points){
        super(points);
    }

    public void use(Entity me){
        if(me.getLocation().getAdjacentAt(me.getDirectionFacing()) != null){
            Location locationFacing = me.getLocation().getAdjacentAt(me.getDirectionFacing());
            if(World.getWorld().getCurrentMap().entityAtLocation(locationFacing) != null){
                Entity otherEntity = World.getWorld().getCurrentMap().entityAtLocation(locationFacing);
                Inventory othersInventory = otherEntity.getInventory();
                if(othersInventory.getItem(0) != null){
                    me.getInventory().addItem(othersInventory.removeItem(0));
                }
            }
        }
    }
}
