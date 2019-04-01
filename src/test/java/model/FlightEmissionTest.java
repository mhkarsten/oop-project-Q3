package model;

import org.junit.jupiter.api.Test;
import server.model.FlightEmission;

import static org.junit.jupiter.api.Assertions.*;

class FlightEmissionTest {

    String carbon = "333.3";
    String carbontest = "823";
    String energy = "3";
    String energytest = "9";
    String fuel_use = "7";
    String fuel_usetest = "837";
    String distance = "847";
    String distancetest = "666";
    FlightEmission fm = new FlightEmission(carbon, energy, fuel_use, distance);

    @Test
    void JSONtoFlight() {
    }

    @Test
    void getCarbon() {
        assertEquals(carbon, fm.getCarbon());
    }

    @Test
    void setCarbon() {
        fm.setCarbon(carbontest);
        assertEquals(carbontest, fm.getCarbon());
    }

    @Test
    void getEnergy() {
        assertEquals(energy, fm.getEnergy());
    }

    @Test
    void setEnergy() {
        fm.setEnergy(energytest);
        assertEquals(energytest, fm.getEnergy());
    }

    @Test
    void getFuelUse() {
        assertEquals(fuel_use, fm.getFuelUse());
    }

    @Test
    void setFuelUse() {
        fm.setFuelUse(fuel_usetest);
        assertEquals(fuel_usetest, fm.getFuelUse());
    }

    @Test
    void getDistance() {
        assertEquals(distance, fm.getDistance());
    }

    @Test
    void setDistance() {
        fm.setDistance(distancetest);
        assertEquals(distancetest, fm.getDistance());
    }
}