package client.model;

public abstract class Emission {
    public String carbon;

    abstract public String getStringName();

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }
}
