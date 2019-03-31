package server.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Flight emission.
 */
public class FlightEmission {
//    INPUT VALUES
//    String startPort;
//    String endPort;

    //RETURN VALUES
    private String carbon;
    private String energy;
    private String distance;
    private String fuelUse;

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
        this.carbon = carbon;
        this.energy = energy;
        this.distance = distance;
        this.fuelUse = fuelUse;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFuelUse() {
        return fuelUse;
    }

    public void setFuelUse(String fuelUse) {
        this.fuelUse = fuelUse;
    }
}
