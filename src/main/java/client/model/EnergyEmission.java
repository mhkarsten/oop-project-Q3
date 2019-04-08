package client.model;

/**
 * The type Energy emission.
 */
public class EnergyEmission extends Emission {
    //    INPUT VALUES
    //    int greenEnergy;
    //    String airConditionerUse;
    //    String dishwasheruse;
    //    int naturalGasCost;

    //RETURN VALUES
    private String carbon;
    private String dirtyEnergy;
    private String naturalGasConsumed;

    /**
     * Instantiates a new Energy emission.
     */
    public EnergyEmission() {

    }

    /**
     * Instantiates a new Energy emission.
     *
     * @param carbon             the carbon
     * @param dirtyEnergy        the dirty energy
     * @param naturalGasConsumed the natural gas consumed
     */
    public EnergyEmission(String carbon, String dirtyEnergy, String naturalGasConsumed) {
        this.carbon = carbon;
        this.dirtyEnergy = dirtyEnergy;
        this.naturalGasConsumed = naturalGasConsumed;
    }

    @Override
    public String toString() {

        return "Carbon Emitted: " + this.getCarbon()
                + "\nDirty Energy Emitted: " + this.getDirtyEnergy()
                + "\nNatural Gas Consumed: " + this.getNaturalGasConsumed();
    }

    public String getStringName() {

        return "Energy Emission";
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getDirtyEnergy() {
        return dirtyEnergy;
    }

    public void setDirtyEnergy(String dirtyEnergy) {
        this.dirtyEnergy = dirtyEnergy;
    }

    public String getNaturalGasConsumed() {
        return naturalGasConsumed;
    }

    public void setNaturalGasConsumed(String naturalGasConsumed) {
        this.naturalGasConsumed = naturalGasConsumed;
    }
}
