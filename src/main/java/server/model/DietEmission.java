package server.model;

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

    /**
     * Jso nto diet diet emission.
     *
     * @param jsonDietEmission the json diet emission
     * @return the diet emission
     */
    public static DietEmission jsonToDiet(JSONObject jsonDietEmission) {

        DietEmission newEmission = new DietEmission();

        LinkedHashMap mainBody = (LinkedHashMap) jsonDietEmission.get("decisions");

        LinkedHashMap carbon = (LinkedHashMap) mainBody.get("carbon");
        newEmission.setCarbon((String) carbon.get("description"));

        LinkedHashMap intensity = (LinkedHashMap) mainBody.get("intensity");
        newEmission.setIntensity((String) intensity.get("description"));

        return newEmission;
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
