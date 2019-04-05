package client.model;

/**
 * The type Diet emission.
 */
public class DietEmission extends Emission {
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

    @Override
    public String toString() {

        return "Carbon emission: " + this.getCarbon()
                + "\nCarbon intensity: " + this.getIntensity();
    }

    public String getStringName() {

        return "Diet Emission";
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
