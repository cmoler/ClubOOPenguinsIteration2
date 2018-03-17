package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.TakeableItem.TakeableItem;

public class TransactionAreaEffect extends OneShotAreaEffect {

    TakeableItem shopItem;

    public TransactionAreaEffect(TakeableItem item){
        shopItem = item;
    }


    @Override
    protected void affect(Entity entity) {
        Player player = (Player) entity;
        if (player.getGold() > shopItem.getValue()){
            player.takeItem(shopItem);
            int itemCost = shopItem.getValue()-player.getBargain();
            player.modifyGold(-itemCost);
            setActive(false);
        }
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TRANSACTION;
    }
}
