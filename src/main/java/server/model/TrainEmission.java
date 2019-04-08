package server.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type train emission.
 */
public class TrainEmission {
    //  INPUT VALUES
    //  int distance; in kilometers
    //  int duration; in seconds

    //RETURN VALUES
    private String carbon;
    private String energy;
    private String fuelUse;
    private String distance;

    /**
     * Instantiates a new Train emission.
     */
    public TrainEmission() {

    }

    /**
     * Instantiates a new Vehicle emission.
     *
     * @param carbon   the carbon
     * @param energy   the energy
     * @param fuelUse  the fuel use
     * @param distance the distance
     */
    public TrainEmission(String carbon, String energy, String fuelUse, String distance) {
        this.carbon = carbon;
        this.energy = energy;
        this.fuelUse = fuelUse;
        this.distance = distance;
    }

    /**
     * Jsontotrain train emission.
     *
     * @param jsonTrainEmission the json train emission
     * @return the train emission
     */
    @SuppressWarnings("Duplicates")
    public static TrainEmission jsonToTrain(JSONObject jsonTrainEmission) {

        TrainEmission newEmission = new TrainEmission();

        LinkedHashMap mainBody = (LinkedHashMap) jsonTrainEmission.get("decisions");

        LinkedHashMap carbon = (LinkedHashMap) mainBody.get("carbon");
        newEmission.setCarbon((String) carbon.get("description"));

        LinkedHashMap energy = (LinkedHashMap) mainBody.get("electricity_consumption");
        newEmission.setEnergy((String) energy.get("description"));

        LinkedHashMap fuelUse = (LinkedHashMap) mainBody.get("diesel_consumption");
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