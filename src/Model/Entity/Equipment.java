package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Entity entity;
    private TakeableItem equipped;

    public Equipment(Entity entity) {
        this.entity = entity;
        observers = new ArrayList<>();
    }

    public boolean equip(TakeableItem item){
        if (equipped == null)
        {
            equipped = item;
            entity.modifyMaxHealth(50);
            notifyView();
            return true;
        }
        return false;
    }

    public boolean unEquip(TakeableItem item){
        if(equipped != null) {
            equipped = null;
            entity.modifyMaxHealth(-50);
            notifyView();
            return true;
        }
        return false;
    }

    public TakeableItem getEquipped() {
        return equipped;
    }

    public void attach(Viewport viewport){
        observers.add(viewport);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView(){
        for( Viewport viewport : observers) {
            viewport.update();
        }
    }
}
