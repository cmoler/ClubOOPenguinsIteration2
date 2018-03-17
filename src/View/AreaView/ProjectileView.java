package View.AreaView;

import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Item.TakeableItem.ProjectileCapableItem;
import Model.Map.Location;
import View.Viewport;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileView extends Viewport {

    private ProjectileCapableItem item;
    private String appearance; // possibilities: "Ice", "Pizza", "Snow"

    private List<Integer> xLocations;
    private List<Integer> yLocations;

    public ProjectileView(ProjectileCapableItem item){
        this.item = item;
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

    public void draw(Graphics2D graphics2D) {
        for(int i=0; i< xLocations.size(); i++){
            // draw image
        }
    }
}
