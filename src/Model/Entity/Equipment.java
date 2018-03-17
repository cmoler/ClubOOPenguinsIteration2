package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Player entity;
    private Inventory inventory;
    private UsableItems hotbar;
    private WearableItems armor;

    private int equipmentSize = 5;

    public Equipment(Player entity) {
        this.entity = entity;
        this.inventory = entity.getInventory();
        hotbar = new UsableItems(equipmentSize);
        armor = new WearableItems();
        observers = new ArrayList<>();
    }

    public boolean equip(TakeableItem item){
        if (item.canEquip(this.entity)) {
            if (hotbar.add(item)) {
                inventory.removeItem(item);
                notifyView();
                return true;
            }
            else
                return false;
        } else if(item.canWear()){
            if(armor.equip((WearableItem)item)){
                inventory.removeItem(item);
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

    public boolean unEquipUsableItem(int index){
        TakeableItem item = hotbar.getItem(index);
        if(hotbar.remove(item)) {
            inventory.addItem(item);
            notifyView();
            return true;
        }
        return false;
    }

    public boolean unEquipWearableItem(String armorType){
        if(armor.getArmor(armorType) != null) {
            WearableItem item = armor.getArmor(armorType);
            if(armor.unequip(item)) {
                inventory.addItem(item);
                notifyView();
                return true;
            }
        }

        return false;
    }

    public TakeableItem getEquipped(int index) {
        return hotbar.getItem(index);
    }

    public void useItem(int index){
        if (hotbar.getItem(index) != null){
            TakeableItem item = hotbar.getItem(index);
            ((UseableItem) item).use(this.entity, this.entity.getLocation());
        }
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
                        wearableItem.putOn(entity);
                        return true;
                    }
                case "body":
                    if(body != null) return false;
                    else {
                        body = wearableItem;
                        wearableItem.putOn(entity);
                        return true;
                    }
                case "legs":
                    if(legs != null) return false;
                    else {
                        legs = wearableItem;
                        wearableItem.putOn(entity);
                        return true;
                    }
                case "ring":
                    if(ring != null) return false;
                    else {
                        ring = wearableItem;
                        wearableItem.putOn(entity);
                        return true;
                    }
            }
            return false;
        }

        public boolean unequip(WearableItem wearableItem){
            if(head == wearableItem){
                head = null;
                wearableItem.takeOff(entity);
                return true;
            }else if(body == wearableItem){
                body = null;
                wearableItem.takeOff(entity);
                return true;
            }else if(legs == wearableItem){
                legs = null;
                wearableItem.takeOff(entity);
                return true;
            }else if(ring == wearableItem){
                ring = null;
                wearableItem.takeOff(entity);
                return true;
            }else {
                return false;
            }
        }

        public WearableItem getArmor(String armorType){
            switch (armorType){
                case "head":
                    return head;
                case "body":
                    return body;
                case "legs":
                    return legs;
                case "ring":
                    return ring;
            }
            return null;
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
