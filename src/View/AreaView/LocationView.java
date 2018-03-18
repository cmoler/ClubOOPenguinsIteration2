package View.AreaView;


import Configs.ImagesInfo;
import Model.Item.Item;
import Model.Map.Location;
import View.AreaView.ItemView.ItemView;
import View.Viewport;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

//    //Readd Items that are still on it
//    public void addItemViews(){
//
//        List<Item> items = location.getItems();
//        List<ItemView> itemViews = new ArrayList<ItemView>();
//        for (int i = 0; i<items.size(); i++){
//            switch (items.get(i).getItemType()){
//                case ONESHOT:
//                    itemViews.add(new ItemView(ImagesInfo.BURGER_IMAGE));
//                    break;
//                case INTERACTIVE:
//                    itemViews.add(new ItemView(ImagesInfo.KEY_IMAGE));
//                    break;
//                case TAKEABLE:
//                    itemViews.add(new ItemView(ImagesInfo.ITEM_TAKEABLE_IMAGE));
//                    break;
//                case TELEPORTER:
//                    itemViews.add(new ItemView(ImagesInfo.ITEM_TELEPORTER_IMAGE));
//                    break;
//            }
//        }
//
//        for(ItemView itemView: itemViews){
//            children.add(itemView);
//        }
//
//    }


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
//
//    @Override
//    public void update(){
//        removeItemViews();
//        addItemViews();
//        repaint();
//    }

}
