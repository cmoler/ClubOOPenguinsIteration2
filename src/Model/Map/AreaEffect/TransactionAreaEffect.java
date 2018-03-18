package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TakeableItemGenerator;

public class TransactionAreaEffect extends OneShotAreaEffect {

    private TakeableItem shopItem;

    public TransactionAreaEffect(){
        shopItem = TakeableItemGenerator.getTakeableItemGenerator().getRandomItem();
    }

    public TakeableItem getShopItem() {
        return shopItem;
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

    public TakeableItem getShopItem() {
        return shopItem;
    }
}
