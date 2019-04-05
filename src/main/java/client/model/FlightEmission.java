package client.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Flight emission.
 */
public class FlightEmission extends Emission {
//    INPUT VALUES
//    String startPort;
//    String endPort;

    //RETURN VALUES
    private String carbon;
    private String energy;
    private String distance;
    private String fuelUse;

    @Override
    public String toString() {

        return "Fuel Use: " + this.getFuelUse()
            + "\nEnergy Used: " + this.getEnergy()
            + "\nDistance Traveled: " + this.getDistance()
            + "\nCarbon Emitted: " + this.getCarbon();
    }

    public String getStringName() {

        return "Flight Emission";
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
