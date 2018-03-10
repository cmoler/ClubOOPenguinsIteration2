package Controller.temp;

import Configs.ImagesInfo;
import Model.Entity.Entity;
import Model.Entity.EntityType;
import Model.Entity.Inventory;
import Model.Item.*;
import Model.Map.AreaEffect.DamageAreaEffect;
import Model.Map.Location;
import Model.Map.Terrain.Ice;
import Model.Map.Terrain.Water;
import Model.Map.World;
import View.AreaView.AvatarView;
import View.AreaView.ItemView.ItemView;
import View.AreaView.TerrainView;
import View.StatusView.StatusViewPort;
import View.Viewport;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityBuilderTemp {

    private AvatarView avatarView;
    private StatusViewPort statusViewport;

    public EntityBuilderTemp(){}

    public Entity buildEntity(String folder, String entityID) throws FileNotFoundException {
        String filename = folder+"/EntityModel" + entityID + ".txt";

        Scanner s = new Scanner(new File(filename));
        List<String> entityData = new ArrayList<String>();
        while (s.hasNextLine()) {
            entityData.add(s.nextLine());
        }

        Entity e;

        int lineIndex = 2;
        EntityType entityType = EntityType.ICE;
        String entityTypeString = entityData.get(lineIndex++).split("\t")[1];
        switch (entityTypeString) {
            case "ICE":
                entityType = EntityType.ICE;
                break;
            case "WATER":
                entityType = EntityType.WATER;
                break;
        }

        String[] locationCoords = entityData.get(lineIndex++).split("\t")[1].split(",");
        int xCoord = Integer.parseInt(locationCoords[1]);
        int yCoord = Integer.parseInt(locationCoords[0]);

        int health = Integer.parseInt(entityData.get(lineIndex++).split("\t")[1]);

        int experience = Integer.parseInt(entityData.get(lineIndex++).split("\t")[1]);

        lineIndex++; // line is "INVENTORY"
        List<TakeableItem> inventoryItems = new ArrayList<TakeableItem>();
        List<TakeableItem> equipmentItems = new ArrayList<TakeableItem>();
        List<ItemView> itemViews = new ArrayList<ItemView>();
        if (lineIndex < entityData.size()){
            while (entityData.get(lineIndex).substring(0, 2).equals("\t\t")) {
                String itemType = entityData.get(lineIndex).split("\t\t")[1];
                switch (itemType) {
                    case "TAKEABLE":
                        inventoryItems.add(new TakeableItem());
                        //                    itemViews.add(new ItemView(Commons.ITEM_TAKEABLE_IMAGE));
                        break;
                    case "EQUIPMENT":
                        lineIndex++;
                        while (entityData.get(lineIndex).substring(0, 3).equals("\t\t\t")) {
                            itemType = entityData.get(lineIndex++).split("\t\t\t")[1];
                            switch (itemType) {
                                case "TAKEABLE":
                                    equipmentItems.add(new TakeableItem());
                                    break;
                            }
                            if (lineIndex >= entityData.size())
                                break;
                        }
                }
                lineIndex++;
                if (lineIndex >= entityData.size())
                    break;
            }
        }

        World world = World.getWorld();
        Location initialLocation = new Location(new Ice(), false, null, null);
        if(world.getCurrentMap() != null)
            initialLocation = world.getCurrentMap().getLocationXY(xCoord,yCoord);
        else
            System.out.println("ERROR: no maps have been loaded, so Entity default location is not right");
        e = new Entity(entityType, initialLocation);


        e.takeDamage(100-health);
        e.gainExperience(experience);
        Inventory i = e.getInventory();
        for (TakeableItem t : inventoryItems){
            i.addItem(t);
        }
        //for (TakeableItem t : equipmentItems){
        //    i.equip(t);
        //}

        //statusViewport = new StatusViewPort(e, i.getEquipment(), i);
        avatarView = new AvatarView(e, xCoord, yCoord);
        return e;
    }

    public Entity buildEntity(String entityID) throws FileNotFoundException {
        return buildEntity("resources/entities", entityID);
    }

    public AvatarView getAvatarView() {
        return avatarView;
    }

    public void setAvatarViewImage(Image e){
        this.avatarView.setAvatarImage(e);
    }

    public StatusViewPort getStatusViewport() {
        return statusViewport;
    }
}
