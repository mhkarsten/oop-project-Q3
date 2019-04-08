package client.model;

public abstract class Emission {
    public String carbon;

    public abstract String getStringName();

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }
}
