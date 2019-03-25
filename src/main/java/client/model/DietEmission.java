package client.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Diet emission.
 */
public class DietEmission {
//    INPUT VALUES
//    float fishShare;
//    float redMeatShare;
//    float poultryShare;
//    int size;
//    String startDate;
//    String endDate;

    //RETURN VALUES
    private String carbon;
    private String intensity;

    public DietEmission() {

    }

    public DietEmission(String carbon, String intensity) {
        this.carbon = carbon;
        this.intensity = intensity;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
}
