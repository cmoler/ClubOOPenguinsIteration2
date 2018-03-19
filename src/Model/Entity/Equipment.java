package Model.Entity;

import Model.Item.Item;
import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Player entity;
    private UsableItems hotbar;
    private WearableItems armor;

    private int equipmentSize = 5;

    private int selected = 0;

    public Equipment(Player entity) {
        this.entity = entity;
        hotbar = new UsableItems(equipmentSize);
        armor = new WearableItems();
        observers = new ArrayList<>();
    }

    public boolean equip(TakeableItem item){
        if (item.canEquip(this.entity)) {
            if (hotbar.add(item)) {
                this.entity.getInventory().removeItem(item);
                notifyView();
                return true;
            }
            else
                return false;
        } else if(item.canWear()){
            if(armor.equip((WearableItem)item)){
                this.entity.getInventory().removeItem(item);
                notifyView();
                return true;
            }
        }
        return false;
    }

    public boolean unEquip(){
        if (selected < equipmentSize){
            TakeableItem item = hotbar.getItem(selected);
            hotbar.remove(item);
            this.entity.getInventory().addItem(item);
            return true;
        }
        else if(selected == 5){
            WearableItem item = armor.getArmor("head");
            armor.unequip(item);
            this.entity.getInventory().addItem(item);
            return true;
        }
        else if(selected == 6){
            WearableItem item = armor.getArmor("body");
            armor.unequip(item);
            this.entity.getInventory().addItem(item);
            return true;
        }
        else if(selected == 7){
            WearableItem item = armor.getArmor("legs");
            armor.unequip(item);
            this.entity.getInventory().addItem(item);
            return true;
        }
        else if(selected == 8){
            WearableItem item = armor.getArmor("ring");
            armor.unequip(item);
            this.entity.getInventory().addItem(item);
            return true;
        }
        return false;
    }

    public boolean unEquip(TakeableItem item){
        return hotbar.remove(item);
    }

    public TakeableItem getSlot(int index) {
        return hotbar.getItem(index);
    }

    public TakeableItem getEquipped(int index) {
        return hotbar.getItem(index);
    }

    public List<TakeableItem> getHotbarItems(){
        return hotbar.getItems();
    }

    public void useItem(int index){
        if (hotbar.getItem(index) != null) {
            System.out.println("item used");
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

    public int getEquipmentSize(){
        return equipmentSize;
    }

    public void notifyView(){
        for( Viewport viewport : observers) {
            viewport.update();
        }
    }

    public WearableItem getHead() {
        return armor.head;
    }

    public WearableItem getBody() {
        return armor.body;
    }

    public WearableItem getLegs() {
        return armor.legs;
    }

    public WearableItem getRing() {
        return armor.ring;
    }

    public void scrollRight(){
        if(selected < equipmentSize + 3){
            selected++;
        }
        notifyView();
    }

    public void scrollLeft(){
        if(selected > 0){
            selected--;
        }
        notifyView();
    }

    public int getSelected(){
        return selected;
    }

    private class WearableItems{
        private WearableItem head = null;
        private WearableItem legs = null;
        private WearableItem body = null;
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

        public List<TakeableItem> getItems() {
            return items;
        }
    }
}
