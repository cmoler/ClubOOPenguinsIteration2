package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Viewport> observers = new ArrayList<Viewport>();

    private List<TakeableItem> items = new ArrayList<>();

    public InventoryIterator getIterator(){
        return new InventoryIterator();
    }

    public Inventory(Entity entity) {

    }

    public boolean addItem(TakeableItem item){
        boolean itemAdded;
        itemAdded = items.add(item);
        if(itemAdded) { notifyView(); }

        return itemAdded;
    }

    public boolean removeItem(TakeableItem item){
        boolean itemRemoved;
        itemRemoved = items.remove(item);
        if(itemRemoved) { notifyView(); }

        return itemRemoved;
    }

    public TakeableItem removeItem(int index){
        TakeableItem removedItem;
        removedItem = items.remove(index);
        notifyView();

        return removedItem;
    }

    public TakeableItem getItem(int index){
        if(index < items.size())
            return items.get(index);
        else
            return null;
    }

    public boolean doesExist(TakeableItem item){
        boolean itemExists;
        itemExists = items.contains(item);

        return itemExists;
    }


    public void attach(Viewport viewport){
        observers.add(viewport);
    }

    public void detach(Viewport viewport){
        observers.remove(viewport);
    }

    public void notifyView(){
        for (Viewport viewport : observers){
            viewport.update();
        }
    }

    public class InventoryIterator{
        public int index;

        public void reset(){
            index = 0;
        }

        public boolean hasNext(){
            return items.size() > index;
        }

        public boolean hasPrev(){
            return index >= 1;
        }

        public void prev(){
            index -= 1;
        }

        public void next(){
            index += 1;
        }

        public TakeableItem getCurrent(){
            if(index < items.size()){
                return items.get(index);
            }
            return null;
        }
    }

}
