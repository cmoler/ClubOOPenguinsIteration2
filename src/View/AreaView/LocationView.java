package View.AreaView;


import Configs.ImagesInfo;
import Model.Item.Item;
import Model.Map.Location;
import View.Viewport;

import java.awt.*;
import java.util.ArrayList;


public class LocationView extends Viewport {

    private Location location;
    private int locationX;
    private int locationY;

    public LocationView(Location location, int x, int y) {
        this.location = location;
        this.locationX = x;
        this.locationY = y;
        location.attach(this);
    }

    @Override
    public void draw(Graphics2D graphics2D, int x, int y){
        for(Viewport child: children){
            child.draw(graphics2D, x, y);
        }
    }


    //Remove all itemViews?
    public void removeItemViews(){
        for (int i = 0; i<children.size(); i++){
            if(children.get(i) instanceof ItemView){
                children.remove(i);
                i--;
            }
        }
    }

    //TODO UNCOMMENT THIS

    //Readd Items that are still on it
    public void addItemViews(){

        java.util.List<Item> items = location.getItems();
        java.util.List<ItemView> itemViews = new ArrayList<ItemView>();

        for (int i = 0; i<items.size(); i++){
            itemViews.add(new ItemView(items.get(i).getName()));
            }

        for(ItemView itemView: itemViews){
            children.add(itemView);
        }

    }


    @Override
    public int getLocationX(){
        return locationX;
    }

    @Override
    public int getLocationY(){
        return locationY;
    }

    public Location getTerrainLocation() {
        return location;
    }

    @Override
    public void update(){
        removeItemViews();
        addItemViews();
        repaint();
    }

}
