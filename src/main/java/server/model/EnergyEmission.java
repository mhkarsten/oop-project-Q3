package server.model;

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

    /**
     * Jsontoenergy energy emission.
     *
     * @param jsonEnergyEmission the json energy emission
     * @return the energy emission
     */
    public static EnergyEmission jsonToEnergy(JSONObject jsonEnergyEmission) {

        EnergyEmission newEmission = new EnergyEmission();

        LinkedHashMap mainBody = (LinkedHashMap) jsonEnergyEmission.get("decisions");

        LinkedHashMap carbon = (LinkedHashMap) mainBody.get("carbon");
        newEmission.setCarbon((String) carbon.get("description"));

        LinkedHashMap dirtyEnergy = (LinkedHashMap) mainBody.get("dirty_electricity_generated");
        newEmission.setDirtyEnergy((String) dirtyEnergy.get("description"));

        LinkedHashMap naturalGas = (LinkedHashMap) mainBody.get("natural_gas_consumed");
        newEmission.setNaturalGasConsumed((String) naturalGas.get("description"));

        return newEmission;
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
