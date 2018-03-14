package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Entity entity;
    private EquippedItems equipped;
    private int equipmentSize = 3;

    public Equipment(Entity entity) {
        this.entity = entity;
        equipped = new EquippedItems(equipmentSize);
        observers = new ArrayList<>();
    }

    public boolean equip(TakeableItem item){
        if (item.canEquip(this.entity)) {
            if (equipped.add(item)) {
                notifyView();
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public boolean unEquip(TakeableItem item){
        if(equipped.remove(item)) {
            notifyView();
            return true;
        }
        return false;
    }

    public TakeableItem getEquipped(int index) {
        return equipped.getItem(index);
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

    private class EquippedItems{
        private List<TakeableItem> items;

        public EquippedItems(int size){
            items = new ArrayList<TakeableItem>(size);
        }

        public boolean add(TakeableItem i){
            if(items.size() < 3) {
                items.add(i);
                return true;
            }
            else
                return false;
        }

        public boolean remove(TakeableItem i){
            if(items.contains(i)) {
                items.remove(i);
                return true;
            }
            else
                return false;
        }

        public TakeableItem getItem(int index){
            if(index < items.size())
                return items.get(index);
            else
                return null;
        }
    }
}
