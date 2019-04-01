package model;

import org.junit.jupiter.api.Test;
import server.model.TrainEmission;

import static org.junit.jupiter.api.Assertions.*;

class TrainEmissionTest {
    String carbon = "389.289";
    String carbontest = "278";
    String energy = "873";
    String energytest = "36785627";
    String fuel_use = "443";
    String fuel_usetest = "7826286";
    String distance = "45";
    String distancetest = "83";
    TrainEmission tm = new TrainEmission(carbon, energy, fuel_use, distance);

    @Test
    void JSONtoTrain() {
    }

    @Test
    void getCarbon() {
        assertEquals(carbon, tm.getCarbon());
    }

    @Test
    void setCarbon() {
        tm.setCarbon(carbontest);
        assertEquals(carbontest, tm.getCarbon());
    }

    @Test
    void getEnergy() {
        assertEquals(energy, tm.getEnergy());
    }

    @Test
    void setEnergy() {
        tm.setEnergy(energytest);
        assertEquals(energytest, tm.getEnergy());
    }

    @Test
    void getDistance() {
        assertEquals(distance, tm.getDistance());
    }

    @Test
    void setDistance() {
        tm.setDistance(distancetest);
        assertEquals(distancetest, tm.getDistance());
    }

    @Test
    void getFuelUse() {
        assertEquals(fuel_use, tm.getFuelUse());
    }

    @Test
    void setFuelUse() {
        tm.setFuelUse(fuel_usetest);
        assertEquals(fuel_usetest, tm.getFuelUse());
    }

}