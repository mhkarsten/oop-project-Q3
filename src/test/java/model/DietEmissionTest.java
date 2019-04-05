package model;

import server.model.DietEmission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DietEmissionTest {
    String carbon = "34.280 kg";
    String carbontest = "99,478";
    String intensity = "367";
    String intensitytest = "111";

    DietEmission dm = new DietEmission(carbon, intensity);

    @Test
    void dietEmissionBasicConstructor() {
        DietEmission dietEmission = new DietEmission();
        assertNotNull(dietEmission);
    }

    @Test
    void getCarbon() {
        assertEquals(carbon, dm.getCarbon());
    }

    @Test
    void setCarbon() {
        dm.setCarbon(carbontest);
        assertEquals(carbontest, dm.getCarbon());
    }

    @Test
    void getDirtyEnergy() {
        assertEquals(intensity, dm.getIntensity());
    }

    @Test
    void setDirtyEnergy() {
        dm.setIntensity(intensitytest);
        assertEquals(intensitytest, dm.getIntensity());
    }

}