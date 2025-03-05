package ro.academyplus.avaj.simulator.tower;

import ro.academyplus.avaj.simulator.flyable.*;
import ro.academyplus.avaj.simulator.service.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates p_coordinates) {
        WeatherProvider weatherMAS = WeatherProvider.getInstance();
        return weatherMAS.getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}
