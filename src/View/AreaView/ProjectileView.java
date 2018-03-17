package View.AreaView;

import Configs.AreaSizes;
import Configs.ImagesInfo;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Map.Location;
import View.Viewport;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileView extends Viewport {

    private MapView parent;
    private ProjectileCapableItem item;
    private String appearance; // possibilities: "Linear Ice", "Angular Ice", "Radial Ice", "Pizza", "Snow"

    private List<Integer> xLocations;
    private List<Integer> yLocations;

    public ProjectileView(ProjectileCapableItem item, MapView parent){
        this.item = item;
        this.parent = parent;
    }

    public void update() {
        xLocations = new ArrayList<Integer>();
        yLocations = new ArrayList<Integer>();
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
        Image image = ImagesInfo.PROJECTILE_LINEARICEATTACK;
        if(appearance.equals("Angular Ice"))
            image = ImagesInfo.PROJECTILE_ANGULARICEATTACK;
        else if(appearance.equals("Radial Ice"))
            image = ImagesInfo.PROJECTILE_RADIALICEATTACK;
        else if(appearance.equals("Pizza"))
            image = ImagesInfo.PROJECTILE_PIZZA;
        else if(appearance.equals("Snow"))
            //
        for(int i=0; i< xLocations.size(); i++){
            Pair<Integer, Integer> location = parent.calculateScreenXY(xLocations.get(i), yLocations.get(i));
            graphics2D.drawImage(image, location.getKey(), location.getValue(),
                    AreaSizes.PROJECTILE_WIDTH, AreaSizes.PROJECTILE_HEIGHT,this );
        }
    }
}
