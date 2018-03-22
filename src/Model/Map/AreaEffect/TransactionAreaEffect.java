package Model.Map.AreaEffect;

import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.TakeableItemGenerator;
import View.AreaView.TransactionAreaEffectView;
import View.AreaView.TrapView;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class TransactionAreaEffect extends OneShotAreaEffect {

    private TakeableItem shopItem;

    public TransactionAreaEffect(){
        shopItem = TakeableItemGenerator.getTakeableItemGenerator().getRandomItem();
    }

    public TakeableItem getShopItem() {
        return shopItem;
    }

    private List<Viewport> observers = new ArrayList<Viewport>();

    @Override
    protected void affect(Entity entity) {
        Player player = (Player) entity;
        if (player.getGold() > shopItem.getValue()){
            player.takeItem(shopItem);
            int itemCost = shopItem.getValue()-player.getBargain();
            player.modifyGold(-itemCost);
            setActive(false);
        }
        notifyView();
    }

    @Override
    public AreaEffectType getAreaEffectType() {
        return AreaEffectType.TRANSACTION;
    }

    public void attach(TransactionAreaEffectView transactionAreaEffectView) {
        observers.add(transactionAreaEffectView);
    }


    public void notifyView(){
        for (Viewport viewport : observers){
            viewport.update();
        }
    }
}
