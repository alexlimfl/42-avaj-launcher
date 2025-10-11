package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.tower.WeatherTower;
import ro.academyplus.avaj.simulator.utils.AppUtils;
import ro.academyplus.avaj.simulator.utils.Logger;

public class Baloon extends Aircraft {

    /*
     * • Baloon:
     * ◦ SUN- Longitude increases with 2, Height increases with 4
     * ◦ RAIN- Height decreases with 5
     * ◦ FOG- Height decreases with 3
     * ◦ SNOW- Height decreases with 15
     */

    public final Object[] SUN = { 2, 0, 4, "Let's enjoy the good weather and take some pics." };
    public final Object[] RAIN = { 0, 0, -5, "Damn you rain! You messed up my baloon." };
    public final Object[] FOG = { 0, 0, -3, "It's so foggy, I can't see anything!" };
    public final Object[] SNOW = { 0, 0, -15, "It's snowing. We're gonna crash." };

    public final Object[][] MOD_DATA = { SUN, RAIN, FOG, SNOW };

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        if (weatherTower.toRemove.contains(this))
            return;

        String currentWeather = weatherTower.getWeather(coordinates);
        Logger.logXnl("Baloon#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", "
                + coordinates.getHeight() + ": ");

        coordinates = AppUtils.updateAircraftCoordinates(currentWeather, coordinates, MOD_DATA);

        if (coordinates.getHeight() <= 0) {
            Logger.log("Baloon#" + name + "(" + id + "): landing.");
            weatherTower.unregister(this);
            Logger.log("Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.");
            return;
        }
    }

    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
        weatherTower.register(this);
        Logger.log(
                "Tower says: " + "Baloon" + "#" + name +
                        "(" + id + ") registered to weather tower.");
    }
}