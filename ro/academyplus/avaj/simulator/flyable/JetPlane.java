package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.tower.WeatherTower;
import ro.academyplus.avaj.simulator.utils.Logger;
import ro.academyplus.avaj.simulator.utils.AppUtils;

public class JetPlane extends Aircraft {

    /*
     * JetPlane:
     * ◦ SUN- Latitude increases with 10, Height increases with 2
     * ◦ RAIN- Latitude increases with 5
     * ◦ FOG- Latitude increases with 1
     * ◦ SNOW- Height decreases with 7
     */

    public final Object[] SUN = { 0, 10, 2, "Perfect weather, let's see what this thing can do!" };
    public final Object[] RAIN = { 0, 5, 0, "It's raining. Better watch out for lightings." };
    public final Object[] FOG = { 0, 1, 0, "Okay time to switch on advance vision!" };
    public final Object[] SNOW = { 0, 0, -7, "OMG! Winter is coming!" };

    public final Object[][] MOD_DATA = { SUN, RAIN, FOG, SNOW };

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        if (weatherTower.toRemove.contains(this))
            return;

        String currentWeather = weatherTower.getWeather(coordinates);
        // Logger.logXnl(currentWeather + ' ');
        Logger.logXnl("JetPlane#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", "
                + coordinates.getHeight() + ": ");

        coordinates = AppUtils.updateAircraftCoordinates(currentWeather, coordinates, MOD_DATA);

        if (coordinates.getHeight() <= 0) {
            Logger.log("JetPlane#" + name + "(" + id + "): landing.");
            weatherTower.unregister(this);
            Logger.log("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
            return;
        }
    }

    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
        weatherTower.register(this);
        Logger.log(
                "Tower says: " + "JetPlane" + "#" + name +
                        "(" + id + ") registered to weather tower.");
    }
}