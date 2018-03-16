package Model.Item.TakeableItem;

import Model.Entity.Entity;
import Model.Item.TakeableItem.Projectile.Projectile;
import Model.Map.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ProjectileCapableItem extends UseableItem {

    private List<Projectile> projectiles = new ArrayList<Projectile>();

    protected void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public List<Projectile> getProjectiles() {
        for(Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();){
            Projectile current = iterator.next();
            if(current.isDone()) {
                iterator.remove();
            }
        }
        return projectiles;
    }
}
