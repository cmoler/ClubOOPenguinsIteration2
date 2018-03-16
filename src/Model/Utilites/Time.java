package Model.Utilites;

import java.util.concurrent.TimeUnit;

public final class Time {

    public static double currentInSeconds(){
        return 0.001 * ((double) System.currentTimeMillis());
    }
}
