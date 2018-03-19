package View.AreaView;

import Configs.AreaSizes;
import Configs.ImagesInfo;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import View.Viewport;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileView extends Viewport {

    private List<MapView> parents = new ArrayList<>();
    private List<Map> maps = new ArrayList<>();
    private ProjectileCapableItem item;
    private String appearance = "Linear Ice"; // possibilities: "Linear Ice", "Angular Ice", "Radial Ice", "Pizza", "Snow"

    private List<Integer> xLocations = new ArrayList<Integer>();
    private List<Integer> yLocations = new ArrayList<Integer>();

    public ProjectileView(ProjectileCapableItem item){
        this.item = item;
    }

    public void addParent(MapView mapView){
        parents.add(mapView);
        mapView.add(this);
    }

    public void addMap(Map map){
        maps.add(map);
    }

    public void update() {
        xLocations.clear();
        yLocations.clear();
        List<Projectile> projectiles = item.getProjectiles();
        for (Projectile projectile : projectiles){
            appearance = projectile.getAppearanceType();
            List<Location> locations = projectile.getLocationsOn();
            for(Location location : locations){
                xLocations.add(location.getxCoordinate());
                yLocations.add(location.getyCoordinate());
            }
        }
    }

    public void draw(Graphics2D graphics2D, int x, int y) {

        update();

        Image image = ImagesInfo.PROJECTILE_LINEARICEATTACK;
        if(appearance.equals("Angular Ice"))
            image = ImagesInfo.PROJECTILE_ANGULARICEATTACK;
        else if(appearance.equals("Radial Ice"))
            image = ImagesInfo.PROJECTILE_RADIALICEATTACK;
        else if(appearance.equals("Pizza"))
            image = ImagesInfo.PROJECTILE_PIZZA;
//        else if(appearance.equals("Snow"))
//            //
        for(int i=0; i < xLocations.size(); i++){
            for(int j=0; j < parents.size(); j++) {
                if(maps.get(j) == World.getWorld().getCurrentMap()) {
                    Pair<Integer, Integer> location = parents.get(j).calculateScreenXY(xLocations.get(i), yLocations.get(i));
                    graphics2D.drawImage(image, location.getKey() * AreaSizes.TERRAIN_WIDTH, location.getValue() * AreaSizes.TERRAIN_HEIGHT,
                            AreaSizes.PROJECTILE_WIDTH, AreaSizes.PROJECTILE_HEIGHT, this);
                }
            }
        }
    }

    public boolean done(){
        return true;
    }
}
