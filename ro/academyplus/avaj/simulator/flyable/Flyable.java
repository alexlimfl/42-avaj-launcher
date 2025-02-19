package ro.academyplus.avaj.simulator.flyable;

import ro.academyplus.avaj.simulator.tower.WeatherTower;

// An interface (abstract class) for objects that can fly.
public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions(); // Abstract method

    public void registerTower(WeatherTower p_Tower) {
        weatherTower = p_Tower;
    }
}
