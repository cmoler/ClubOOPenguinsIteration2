package Model.Item.TakeableItem.OneHandedWeaponItem;

import Controller.SavingLoading.Saver;
import Model.Entity.Entity;
import Model.Entity.Role.Smasher;
import Model.Item.TakeableItem.TakeableItem;
import Model.Map.Direction;
import Model.Map.Location;
import Model.Map.Map;
import Model.Map.World;
import Model.Utilites.Time;

import java.util.concurrent.TimeUnit;

public class BlueLightsaber extends OneHandedWeaponItem{

    public BlueLightsaber(){
        super();
        this.name = "blueLightsaber";
        this.color = "blue";
    }

    private double damageAmount = 1.0; // gets multiplied by skill level
    private double secondsPerUse = 1.25;

    @Override
    protected double getSecondsPerUse() {
        return secondsPerUse;
    }

    @Override
    protected double getDamageAmount() {
        return damageAmount;
    }

    @Override
    public String save(Saver saver) {
        return saver.saveBlueLightSaber(this);
    }
}
