package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.service.Logger;
import ro.academyplus.avaj.simulator.tower.WeatherTower;

public class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        /*
         * JetPlane:
         ◦ SUN- Latitude increases with 10, Height increases with 2
         ◦ RAIN- Latitude increases with 5
         ◦ FOG- Latitude increases with 1
         ◦ SNOW- Height decreases with 7
         */
        if (coordinates.getHeight() <= 0)
            return;
        String currentWeather = weatherTower.getWeather(coordinates);
        Logger.logXnl("JetPlane#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", " + coordinates.getHeight() + ": ");
        if (currentWeather == "SUN") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2 > 100 ? 100 : coordinates.getHeight() + 2);
            Logger.log("Perfect weather, let's see what this thing can do!");
        } else if (currentWeather == "RAIN") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
            Logger.log("It's raining. Better watch out for lightings.");
        } else if (currentWeather == "FOG") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
            Logger.log("Okay time to switch on advance vision!");
        } else if (currentWeather == "SNOW") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
            Logger.log("OMG! Winter is coming!");
        }
        if (coordinates.getHeight() <= 0) {
            Logger.log("JetPlane#" + name + "(" + id + "): landing.");
            weatherTower.unregister(null);
            Logger.log("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower");
        }
    }

    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
        weatherTower.register(this);
        // Tower says: Baloon#B1(1) registered to weather tower.
        Logger.log(
            "Tower says: " + "JetPlane" + "#" + name + 
            "(" + id + ") registered to weather tower.");
    }
}