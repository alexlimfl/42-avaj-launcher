package ro.academyplus.avaj.simulator.utils;

import ro.academyplus.avaj.simulator.flyable.Coordinates;

public final class AppUtils {

    public enum WEATHER {
        SUN,
        RAIN,
        FOG,
        SNOW
    }

    public enum COORDINATE {
        LONGITUDE,
        LATITUDE,
        HEIGHT,
        MSG
    }

    public static Coordinates updateAircraftCoordinates(String currentWeather, Coordinates c, Object[][] modificationData) {
        int[] currentCoordinates = {
            c.getLongitude(),
            c.getLatitude(),
            c.getHeight()
        };

        int[] toChangeCoordinates = {
            (int) modificationData[WEATHER.valueOf(currentWeather).ordinal()][COORDINATE.LONGITUDE.ordinal()],
            (int) modificationData[WEATHER.valueOf(currentWeather).ordinal()][COORDINATE.LATITUDE.ordinal()],
            (int) modificationData[WEATHER.valueOf(currentWeather).ordinal()][COORDINATE.HEIGHT.ordinal()],
        };

        int[] updatedCoordinates = {
            currentCoordinates[COORDINATE.LONGITUDE.ordinal()] + toChangeCoordinates[COORDINATE.LONGITUDE.ordinal()],
            currentCoordinates[COORDINATE.LATITUDE.ordinal()] + toChangeCoordinates[COORDINATE.LATITUDE.ordinal()],
            currentCoordinates[COORDINATE.HEIGHT.ordinal()] + toChangeCoordinates[COORDINATE.HEIGHT.ordinal()],
        };

        c = new Coordinates(
            updatedCoordinates[COORDINATE.LONGITUDE.ordinal()],
            updatedCoordinates[COORDINATE.LATITUDE.ordinal()],
            updatedCoordinates[COORDINATE.HEIGHT.ordinal()]
        );

        String pilotMessage = (String) modificationData[WEATHER.valueOf(currentWeather).ordinal()][COORDINATE.MSG.ordinal()];
        Logger.log(pilotMessage);

        return c;
    }

}
