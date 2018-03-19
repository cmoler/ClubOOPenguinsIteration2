package Model.Entity;

import Configs.InventorySizes;
import Model.Item.TakeableItem.TakeableItem;
import View.Viewport;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Viewport> observers = new ArrayList<Viewport>();

    private List<TakeableItem> items = new ArrayList<>();

    private int selectedX = 0;
    private int selectedY = 0;

    public InventoryIterator getIterator(){
        return new InventoryIterator();
    }

    public Inventory() {

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

    public TakeableItem getItemNamed(String name){
        InventoryIterator inventoryIterator = new InventoryIterator();
        while(inventoryIterator.hasNext()){
            TakeableItem item = inventoryIterator.getCurrent();
            if (item.getName() == name){
                return item;
            }
        }
        return null;
    }
    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public int getSelectedIndex() { return selectedX + ( selectedY * selectedX ); }

    public void scrollHorizontal(int i){
        selectedX += i;
        if(items.size() < InventorySizes.INVENTORY_ROWS && selectedX < 0) selectedX = items.size();
        if(items.size() < selectedX ) selectedX = items.size();
        if(selectedX < 0) selectedX = InventorySizes.INVENTORY_ROWS;
        else if(selectedX > InventorySizes.INVENTORY_ROWS) selectedX = 0;
        notifyView();
    }

    public void scrollVeritical(int i){
        selectedY += i;
        int numberOfColumns = (int)Math.ceil(((double)items.size())/InventorySizes.INVENTORY_COLUMNS);

        if(selectedY < 0) selectedY = numberOfColumns;
        else if(selectedY > numberOfColumns) selectedY = 0;
        notifyView();
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
