package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.service.Logger;

import ro.academyplus.avaj.simulator.tower.WeatherTower;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        /*
        • Helicopter:
        ◦ SUN- Longitude increases with 10, Height increases with 2
        ◦ RAIN- Longitude increases with 5
        ◦ FOG- Longitude increases with 1
        ◦ SNOW- Height decreases with 12
        */
        if (coordinates.getHeight() <= 0)
            return;
        String currentWeather = weatherTower.getWeather(coordinates);
        Logger.logXnl("Helicopter#" + name + "(" + id + "): ");
        Logger.logXnl("Coordinates: " + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", " + coordinates.getHeight() + ": ");
        if (currentWeather == "SUN") {
            coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2 > 100 ? 100 : coordinates.getHeight() + 2);
            Logger.log("This is hot");
        } else if (currentWeather == "RAIN") {
            coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
            Logger.log("It's raining, let's get out of here!");
        } else if (currentWeather == "FOG") {
            coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
            Logger.log("I can't see anything, have to change our path!");
        } else if (currentWeather == "SNOW") {
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
            Logger.log("My rotor is going to freeze!");
        }
        if (coordinates.getHeight() <= 0) {
            Logger.log("Helicopter#" + name + "(" + id + "): landing.");
            weatherTower.unregister(this);
            Logger.log("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower");
        }
    }

    public void registerTower(WeatherTower p_Tower) {
    weatherTower = p_Tower;
    weatherTower.register(this);
    // Tower says: Baloon#B1(1) registered to weather tower.
    Logger.log(
        "Tower says: " + "Helicopter" + "#" + name + 
        "(" + id + ") registered to weather tower.");
    }
}