package server.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Flight emission.
 */
public class FlightEmission extends VehicleEmission {

    /**
     * Instantiates a new Flight emission.
     */
    public FlightEmission() {

    }

    /**
     * Instantiates a new Flight emission.
     *
     * @param carbon   the carbon
     * @param energy   the energy
     * @param distance the distance
     * @param fuelUse  the fuel use
     */
    public FlightEmission(String carbon, String energy, String distance, String fuelUse) {
        setCarbon(carbon);
        setEnergy(energy);
        setDistance(distance);
        setFuelUse(fuelUse);
    }

    /**
     * Jso nto flight flight emission.
     *
     * @param JSONFlightEmission the json flight emission
     * @return the flight emission
     */
    @SuppressWarnings("Duplicates")
    public static FlightEmission JSONtoFlight(JSONObject JSONFlightEmission) {

        FlightEmission newEmission = new FlightEmission();

        LinkedHashMap mainBody = (LinkedHashMap) JSONFlightEmission.get("decisions");

        LinkedHashMap carbon = (LinkedHashMap) mainBody.get("carbon");
        newEmission.setCarbon((String) carbon.get("description"));

        LinkedHashMap energy = (LinkedHashMap) mainBody.get("energy");
        newEmission.setEnergy((String) energy.get("description"));

        LinkedHashMap fuelUse = (LinkedHashMap) mainBody.get("fuel_use");
        newEmission.setFuelUse((String) fuelUse.get("description"));

        LinkedHashMap distance = (LinkedHashMap) mainBody.get("distance");
        newEmission.setDistance((String) distance.get("description"));

        return newEmission;
    }

}
