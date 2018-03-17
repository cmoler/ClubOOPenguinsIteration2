package Controller.temp;

import Configs.ImagesInfo;
import Model.Item.*;
import Model.Item.InteractiveItem.InteractiveItem;
import Model.Item.OneShotItem.OneShotItem;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.AreaEffect.*;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.Terrain.Glacier;
import Model.Map.Terrain.Ice;
import Model.Map.Terrain.Terrain;
import Model.Map.Terrain.Water;
import Model.Map.World;
import View.AreaView.*;
import View.AreaView.ItemView.ItemView;
import View.Viewport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapBuilderTemp {

    public MapBuilderTemp(){}

    private MapView viewport = new MapView();

    public MapView getViewport() {
        return viewport;
    }

    public Map buildMap(String folder, String mapID) throws FileNotFoundException {
        viewport = new MapView();

        String filename = folder+"/MapModel" + mapID + ".txt";

        Scanner s = new Scanner(new File(filename));
        List<String> mapData = new ArrayList<String>();
        while (s.hasNextLine()) {
            mapData.add(s.nextLine());
        }

        Map map;

        // first 2 lines are mapID and "MAP"
        int lineIndex = 2;
        int height = Integer.parseInt(mapData.get(lineIndex++).split("\t")[1]);
        int width = Integer.parseInt(mapData.get(lineIndex++).split("\t")[1]);

        Location[][] locations = new Location[height][width];

        String[] defaultPositions = mapData.get(lineIndex++).split("\t")[1].split(",");
        int defaultX = Integer.parseInt(defaultPositions[0]);
        int defaultY = Integer.parseInt(defaultPositions[1]);
        lineIndex++; // line is "LOCATIONS"
        while(mapData.get(lineIndex).substring(0,2).equals("\t\t")){
            // Getting location
            String[] locationCoords = mapData.get(lineIndex++).split("\t\t")[1].split(",");
            int xCoord = Integer.parseInt(locationCoords[1]);
            int yChord = Integer.parseInt(locationCoords[0]);

            //Getting Terrain
            Terrain terrain = null;
            TerrainView terrainView = null;
            String terrainType = mapData.get(lineIndex++).split("\t\t\t")[1];
            switch (terrainType){
                case "ICE":
                    terrain = new Ice();
                    terrainView = new TerrainView(ImagesInfo.ICE_IMAGE);
                    break;
                case "WATER":
                    terrain = new Water();
                    terrainView = new TerrainView(ImagesInfo.WATER_IMAGE);
                    break;
                case "GLACIER":
                    terrain = new Glacier();
                    terrainView = new TerrainView(ImagesInfo.GLACIER_IMAGE);
                    break;
            }

            // Getting Obstacle
            String obstacle = mapData.get(lineIndex++).split("\t\t\t")[1];
            boolean obstacleBool = false;
            ObstacleView obstacleView = null;
            if(obstacle.equals("TRUE")){
                obstacleBool = true;
                obstacleView = new ObstacleView(ImagesInfo.OBSTACLE_IMAGE);
            }

            // Getting AreaEffect
            String areaEffectType = mapData.get(lineIndex++).split("\t\t\t")[1];
            AreaEffect areaEffect;
            AreaEffectView areaEffectView = null;
            DecalView decalView = null;
            switch (areaEffectType){
                case "DAMAGE":
                    areaEffect = new DamageAreaEffect();
                    areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_DAMAGE_IMAGE);
                    break;
                case "HEAL":
                    areaEffect = new HealAreaEffect();
                    areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_HEAL_IMAGE);
                    decalView = new DecalView(ImagesInfo.RED_CROSS_IMAGE);
                    break;
                case "KILL":
                    areaEffect = new KillAreaEffect();
                    areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_KILL_IMAGE);
                    decalView = new DecalView(ImagesInfo.SKULL_CROSS_BONES_IMAGE);
                    break;
                case "LEVELUP":
                    areaEffect = new LevelUpAreaEffect();
                    areaEffectView = new AreaEffectView(ImagesInfo.AREAEFFECT_LEVELUP_IMAGE);
                    decalView = new DecalView(ImagesInfo.GOLD_STAR_IMAGE);
                    break;
                default:
                    areaEffect = null;
            }

            // Get Items
            lineIndex++; // line is "ITEMS"
            List<Item> items = new ArrayList<Item>();
            List<ItemView> itemViews = new ArrayList<ItemView>();
            if(lineIndex < mapData.size()) {
                while (mapData.get(lineIndex).substring(0, 4).equals("\t\t\t\t")) {
                    String itemType = mapData.get(lineIndex).split("\t\t\t\t")[1];
                    switch (itemType) {
                        case "INTERACTIVE":
                            items.add(new InteractiveItem());
                            itemViews.add(new ItemView(ImagesInfo.ITEM_ITERACTIVE_IMAGE));
                            break;
                        case "ONESHOT":
                            items.add(new OneShotItem());
                            itemViews.add(new ItemView(ImagesInfo.ITEM_ONESHOT_IMAGE));
                            break;
                        case "TAKEABLE":
                            items.add(new TakeableItem());
                            itemViews.add(new ItemView(ImagesInfo.ITEM_TAKEABLE_IMAGE));
                            break;
                        case "TELEPORTER":
                            lineIndex++;
                            String teleporterMapID = mapData.get(lineIndex++).split("\t\t\t\t\t")[1];
                            String[] teleporterLocationXAndY = mapData.get(lineIndex).split("\t\t\t\t\t")[1].split(",");
                            int teleportLocationX = Integer.parseInt(teleporterLocationXAndY[0]);
                            int teleportLocationY = Integer.parseInt(teleporterLocationXAndY[1]);
                            items.add(new Teleporter(teleporterMapID, teleportLocationX, teleportLocationY));
                            itemViews.add(new ItemView(ImagesInfo.ITEM_TELEPORTER_IMAGE));
                            break;
                    }
                    lineIndex++;
                    if (lineIndex >= mapData.size())
                        break;
                }
            }

            locations[yChord][xCoord] = new Location(terrain, obstacleBool, areaEffect, items);
            LocationView locationView = new LocationView(locations[yChord][xCoord], xCoord, yChord);
            if(terrainView != null) locationView.add(terrainView);
            if(areaEffectView != null) locationView.add(areaEffectView);
            if(decalView != null) locationView.add(decalView);
            if(obstacleView != null) locationView.add(obstacleView);
            for(ItemView itemView: itemViews){
                locationView.add(itemView);
            }
            this.viewport.add(locationView);

            if(lineIndex >= mapData.size())
                break;
        }

//         if we ever want to include Entities
//        lineIndex++; // line is "ENTITIES"
//        while(lineIndex < mapData.size()){
//            String[] entityPositions = mapData.get(lineIndex++).split("\t")[1].split(",");
//            int entityX = Integer.parseInt(entityPositions[0]);
//            int entityY = Integer.parseInt(entityPositions[1]);
//            String terrainType = mapData.get(lineIndex++).split("\t")[1];
//            EntityType entityType;
//            switch (terrainType){
//                case "ICE":
//                    entityType = EntityType.ICE;
//                    break;
//                case "WATER":
//                    entityType = EntityType.WATER;
//                    break;
///*                case "GLACIER":
//                    entityType = EntityType.GLACIER;
//                    break;*/
//            }
//            Entity e = new Entity(locations[entityX][entityY]);
//        }
        return new Map(locations, locations[defaultY][defaultX]);
    }

    public Map buildMap(String mapID) throws FileNotFoundException {
        return buildMap("resources/maps", mapID);
    }

}