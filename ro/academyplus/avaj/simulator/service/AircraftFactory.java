package ro.academyplus.avaj.simulator.service;

import ro.academyplus.avaj.simulator.flyable.Baloon;
import ro.academyplus.avaj.simulator.flyable.Coordinates;
import ro.academyplus.avaj.simulator.flyable.Flyable;
import ro.academyplus.avaj.simulator.flyable.Helicopter;
import ro.academyplus.avaj.simulator.flyable.JetPlane;

public class AircraftFactory {
    private static AircraftFactory single_instance = null;
    private static long id_num = 0;

    public static synchronized AircraftFactory getInstance() {
        if (single_instance == null)
            single_instance = new AircraftFactory();
        return single_instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        String str = p_type.toLowerCase();
        id_num++;
        if (str.equals("helicopter"))
            return new Helicopter(id_num, p_name, p_coordinates);
        if (str.equals("jetplane"))
            return new JetPlane(id_num, p_name, p_coordinates);
        if (str.equals("baloon"))
            return new Baloon(id_num, p_name, p_coordinates);
        return null;
    }
}
