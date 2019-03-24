package client.model;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;

/**
 * The type Energy emission.
 */
public class EnergyEmission {
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
