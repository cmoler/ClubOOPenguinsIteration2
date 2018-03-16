package Model.Entity.NPC;

import Model.Entity.Role.Role;
import Model.Item.Item;
import java.util.List;

public class ShopKeepNPC extends NPC {

    private List<Item> sellableItems;

    public ShopKeepNPC(Role role, List<Item> items) {
        sellableItems = items;
    }

    public void openShop(){}

    public void closeShop(){}

    public void sellItem(){

    }

    @Override
    public void talk(){
        openShop();
    }
}
