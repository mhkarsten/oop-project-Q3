package client.model;

import org.junit.jupiter.api.Test;
import client.model.EnergyEmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnergyEmissionTest {
    String carbon = "34.280 kg";
    String carbontest = "99,478";
    String dirtyenergy = "367";
    String dirtyenergytest = "111";
    String naturalgasconsumed = "222";
    String naturalgasconsumedtest = "333";
    EnergyEmission em = new EnergyEmission(carbon, dirtyenergy, naturalgasconsumed);

    @Test
    void energyEmissionBasicConstructor() {
        EnergyEmission energyEmission = new EnergyEmission();
        assertNotNull(energyEmission);
    }

    @Test
    void getCarbon() {
        assertEquals(carbon, em.getCarbon());
    }

    @Test
    void setCarbon() {
        em.setCarbon(carbontest);
        assertEquals(carbontest, em.getCarbon());
    }

    @Test
    void getDirtyEnergy() {
        assertEquals(dirtyenergy, em.getDirtyEnergy());
    }

    @Test
    void setDirtyEnergy() {
        em.setDirtyEnergy(dirtyenergytest);
        assertEquals(dirtyenergytest, em.getDirtyEnergy());
    }

    @Test
    void getNaturalGasConsumed() {
        assertEquals(naturalgasconsumed, em.getNaturalGasConsumed());
    }

    @Test
    void setNaturalGasConsumed() {
        em.setNaturalGasConsumed(naturalgasconsumedtest);
        assertEquals(naturalgasconsumedtest, em.getNaturalGasConsumed());
    }
}