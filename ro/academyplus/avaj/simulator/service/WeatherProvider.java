package ro.academyplus.avaj.simulator.service;

import ro.academyplus.avaj.simulator.flyable.Coordinates;

public class WeatherProvider {
    private String[] weather;

    private static void WeatherProvider() {

    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        return weather[0];
    }
}
