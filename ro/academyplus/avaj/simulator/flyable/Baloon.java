package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.tower.WeatherTower;
import ro.academyplus.avaj.simulator.service.Logger;

public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);

    }

    public void updateConditions() {
        /*
        • Baloon:
        ◦ SUN- Longitude increases with 2, Height increases with 4
        ◦ RAIN- Height decreases with 5
        ◦ FOG- Height decreases with 3
        ◦ SNOW- Height decreases with 15
        */
        if (coordinates.getHeight() <= 0)
            return;
        String currentWeather = weatherTower.getWeather(coordinates);
        Logger.logXnl("Baloon#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", " + coordinates.getHeight() + ": ");
        if (currentWeather == "SUN") {
            coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4 > 100 ? 100 : coordinates.getHeight() + 4);
            Logger.log("Let's enjoy the good weather and take some pics.");
        } else if (currentWeather == "RAIN") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
            Logger.log("Damn you rain! You messed up my baloon.");
        } else if (currentWeather == "FOG") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
            Logger.log("It's so foggy, I can't see anything!");
        } else if (currentWeather == "SNOW") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
            Logger.log("It's snowing. We're gonna crash.");
        }
        if (coordinates.getHeight() <= 0) {
            Logger.log("Baloon#" + name + "(" + id + "): landing.");
            weatherTower.unregister(this);
            Logger.log("Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower");
        }
    }
    
    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
        weatherTower.register(this);
        // Tower says: Baloon#B1(1) registered to weather tower.
        Logger.log(
            "Tower says: " + "Baloon" + "#" + name + 
            "(" + id + ") registered to weather tower.");
    }
}