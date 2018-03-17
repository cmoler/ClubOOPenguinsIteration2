package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import Model.Item.TakeableItem.UseableItem;
import Model.Item.TakeableItem.WearableItem;
import View.Viewport;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private List<Viewport> observers;

    private Player entity;
    private UsableItems hotbar;
    private WearableItems armor;

    private int equipmentSize = 5;

    public Equipment(Player entity) {
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

    public TakeableItem getSlot(int index) {
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

    public void scrollArmorX(int i){
        if(i > 0) {
            armor.selectedX++;
            if(armor.selectedX > 1) armor.selectedX = 0;
        }
        if(i < 0) {
            armor.selectedX--;
            if(armor.selectedX < 0) armor.selectedX = 1;
        }
    }

    public void scrollArmorY(int i){
        if(i > 0) {
            armor.selectedY++;
            if(armor.selectedY > 2) armor.selectedY = 0;
        }
        if(i < 0) {
            armor.selectedY--;
            if(armor.selectedY < 0) armor.selectedY = 2;
        }
    }

    public Pair<Integer, Integer> getSelectedArmor(){
        return new Pair<Integer, Integer>(armor.selectedX, armor.selectedY);
    }

    private class WearableItems{
        private WearableItem head = null;
        private WearableItem body = null;
        private WearableItem legs = null;
        private WearableItem ring = null;

        private int selectedX = 0;
        private int selectedY = 0;

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
