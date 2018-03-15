package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Entity entity;
    private UsableItems hotbar;
    private WearableItems armor;

    private int equipmentSize = 5;

    public Equipment(Entity entity) {
        this.entity = entity;
        hotbar = new UsableItems(equipmentSize);
        armor = new WearableItems();
        observers = new ArrayList<>();
    }

    public boolean equip(TakeableItem item){
        if (item.canEquip(this.entity)) {
            if (hotbar.add(item)) {
                notifyView();
                return true;
            }
            else
                return false;
        } else if(item.canWear()){
            if(armor.equip((WearableItem)item)){
                notifyView();
                return true;
            }
        }
        return false;
    }

    public boolean unEquip(TakeableItem item){
        if(hotbar.remove(item)) {
            notifyView();
            return true;
        }
        return false;
    }

    public TakeableItem getEquipped(int index) {
        return hotbar.getItem(index);
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

    private class WearableItems{
        private WearableItem head = null;
        private WearableItem body = null;
        private WearableItem legs = null;
        private WearableItem ring = null;

        public boolean equip(WearableItem wearableItem){
            switch (wearableItem.getSlot()){
                case "head":
                    if(head != null) return false;
                    else {
                        head = wearableItem;
                        entity.addResistance(wearableItem.getResistance());
                        return true;
                    }
                case "body":
                    if(body != null) return false;
                    else {
                        body = wearableItem;
                        entity.addResistance(wearableItem.getResistance());
                        return true;
                    }
                case "legs":
                    if(legs != null) return false;
                    else {
                        legs = wearableItem;
                        entity.addResistance(wearableItem.getResistance());
                        return true;
                    }
                case "ring":
                    if(ring != null) return false;
                    else {
                        ring = wearableItem;
                        entity.addResistance(wearableItem.getResistance());
                        return true;
                    }
            }
            return false;
        }

        public boolean unequip(WearableItem wearableItem){
            if(head == wearableItem){
                head = null;
                entity.decreaseResistance(wearableItem.getResistance());
                return true;
            }else if(body == wearableItem){
                body = null;
                entity.decreaseResistance(wearableItem.getResistance());
                return true;
            }else if(legs == wearableItem){
                legs = null;
                entity.decreaseResistance(wearableItem.getResistance());
                return true;
            }else if(ring == wearableItem){
                ring = null;
                entity.decreaseResistance(wearableItem.getResistance());
                return true;
            }else {
                return false;
            }
        }
    }

    private class UsableItems{
        private List<TakeableItem> items;

        public UsableItems(int size){
            items = new ArrayList<TakeableItem>(size);
        }

        public boolean add(TakeableItem i){
            if(items.size() < equipmentSize) {
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
