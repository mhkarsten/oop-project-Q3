package server.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Vehicle emission.
 */
public class VehicleEmission {
    //    INPUT VALUES
    //    int distance; in kilometers
    //    int duration; in seconds
    //    String sizeClass; a description (i.e. midsized)

    //RETURN VALUES
    private String carbon;
    private String energy;
    private String fuelUse;
    private String distance;

    /**
     * Instantiates a new Vehicle emission.
     */
    public VehicleEmission() {

    }

    /**
     * Instantiates a new Vehicle emission.
     *
     * @param carbon   the carbon
     * @param energy   the energy
     * @param fuelUse  the fuel use
     * @param distance the distance
     */
    public VehicleEmission(String carbon, String energy, String fuelUse, String distance) {
        this.carbon = carbon;
        this.energy = energy;
        this.fuelUse = fuelUse;
        this.distance = distance;
    }

    /**
     * Json to vehicle vehicle emission.
     *
     * @param jsonVehicleEmission the json vehicle emission
     * @return the vehicle emission
     */
    @SuppressWarnings("Duplicates")
    public static VehicleEmission jsonToVehicle(JSONObject jsonVehicleEmission) {

        VehicleEmission newEmission = new VehicleEmission();

        LinkedHashMap mainBody = (LinkedHashMap) jsonVehicleEmission.get("decisions");

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

    public String getFuelUse() {
        return fuelUse;
    }

    public void setFuelUse(String fuelUse) {
        this.fuelUse = fuelUse;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
