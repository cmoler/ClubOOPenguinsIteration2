package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TakeableItemGenerator;

public class TransactionAreaEffect extends OneShotAreaEffect {

    TakeableItem shopItem;

    public TransactionAreaEffect(){
        shopItem = TakeableItemGenerator.getTakeableItemGenerator().getRandomItem();
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
