package ro.academyplus.avaj.simulator.service;

import ro.academyplus.avaj.simulator.flyable.Coordinates;

public class WeatherProvider {
    private static WeatherProvider single_instance = null;
    private static String[] weather = {"SUN", "RAIN", "SNOW", "SNOW"};

    public static synchronized WeatherProvider getInstance() {
        if (single_instance == null)
            single_instance = new WeatherProvider();
        return single_instance;
    }

    private static void WeatherProvider() {
        // Does nothing
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int index = (p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight()) % weather.length;
        return weather[index];
    }
}
