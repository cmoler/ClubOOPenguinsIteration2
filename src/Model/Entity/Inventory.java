package Model.Entity;

import Model.Item.TakeableItem.TakeableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Viewport> observers;

    private List<TakeableItem> items;

    private int selectedX = 0;
    private int selectedY = 0;

    public InventoryIterator getIterator(){
        return new InventoryIterator();
    }

    public Inventory(Entity entity) {
        observers = new ArrayList<>();
        items = new ArrayList<>();
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

    public boolean doesExist(TakeableItem item){
        boolean itemExists;
        itemExists = items.contains(item);

        return itemExists;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public void scrollHorizontal(int i){
        selectedX += i;
        if(selectedX < 0) selectedX = 7;
        else if(selectedX > 7) selectedX = 0;
    }

    public void scrollVeritical(int i){
        selectedY += i;
        int numberOfColumns = (int)Math.ceil(((double)items.size())/7.0);

        if(selectedY < 0) selectedY = numberOfColumns;
        else if(selectedY > numberOfColumns) selectedY = 0;
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
