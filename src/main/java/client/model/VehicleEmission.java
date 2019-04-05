package client.model;

/**
 * The type Vehicle emission.
 */
public class VehicleEmission extends Emission {
//    INPUT VALUES
//    int distance; in kilometers
//    int duration; in seconds
//    String sizeClass; a description (i.e. midsized)

    //RETURN VALUES
    private String carbon;
    private String energy;
    private String fuelUse;
    private String distance;

    @Override
    public String toString() {

        return "Fuel Use: " + this.getFuelUse()
                + "\nEnergy Used: " + this.getEnergy()
                + "\nDistance Traveled: " + this.getDistance()
                + "\nCarbon Emitted: " + this.getCarbon();
    }

    public String getStringName() {

        return "Vehicle Emission";
    }

    /**
     * Instantiates a new Vehicle emission.
     */
    //Basic Constructor
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
