package Model.Item.TakeableItem.Projectile;

import Model.Map.Location;
import Model.Updateable;

import java.util.List;

public interface Projectile extends Updateable {

    List<Location> getLocationsOn();

    String getAppearanceType();
}
