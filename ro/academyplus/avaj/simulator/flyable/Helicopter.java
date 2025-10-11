package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.tower.WeatherTower;
import ro.academyplus.avaj.simulator.utils.AppUtils;
import ro.academyplus.avaj.simulator.utils.Logger;

public class Helicopter extends Aircraft {

    /*
     * • Helicopter:
     * ◦ SUN- Longitude increases with 10, Height increases with 2
     * ◦ RAIN- Longitude increases with 5
     * ◦ FOG- Longitude increases with 1
     * ◦ SNOW- Height decreases with 12
     */

    public final Object[] SUN = { 10, 0, 2, "This is hot!" };
    public final Object[] RAIN = { 5, 0, 0, "It's raining, let's get out of here!" };
    public final Object[] FOG = { 1, 0, 0, "I can't see anything, have to change our path!" };
    public final Object[] SNOW = { 0, 0, -12, "My rotor is going to freeze!" };

    public final Object[][] MOD_DATA = { SUN, RAIN, FOG, SNOW };

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        if (weatherTower.toRemove.contains(this))
            return;

        String currentWeather = weatherTower.getWeather(coordinates);
        Logger.logXnl("Helicopter#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", "
                + coordinates.getHeight() + ": ");

        coordinates = AppUtils.updateAircraftCoordinates(currentWeather, coordinates, MOD_DATA);

        if (coordinates.getHeight() <= 0) {
            Logger.log("Helicopter#" + name + "(" + id + "): landing.");
            weatherTower.unregister(this);
            Logger.log("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
            return;
        }
    }

    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
        weatherTower.register(this);
        Logger.log(
                "Tower says: " + "Helicopter" + "#" + name +
                        "(" + id + ") registered to weather tower.");
    }
}