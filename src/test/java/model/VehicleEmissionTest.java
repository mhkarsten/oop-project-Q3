package model;

import org.junit.jupiter.api.Test;
import server.model.VehicleEmission;

import static org.junit.jupiter.api.Assertions.*;

class VehicleEmissionTest {

    String carbon = "3.9";
    String carbontest = "8";
    String energy = "73";
    String energytest = "67";
    String fuel_use = "3";
    String fuel_usetest = "376";
    String distance = "888";
    String distancetest = "12345";
    VehicleEmission vm = new VehicleEmission(carbon, energy, fuel_use, distance);

    @Test
    void JSONtoVehicle() {
    }

    @Test
    void getCarbon() {
        assertEquals(carbon, vm.getCarbon());
    }

    @Test
    void setCarbon() {
        vm.setCarbon(carbontest);
        assertEquals(carbontest, vm.getCarbon());
    }

    @Test
    void getEnergy() {
        assertEquals(energy, vm.getEnergy());
    }

    @Test
    void setEnergy() {
        vm.setEnergy(energytest);
        assertEquals(energytest, vm.getEnergy());
    }

    @Test
    void getFuelUse() {
        assertEquals(fuel_use, vm.getFuelUse());
    }

    @Test
    void setFuelUse() {
        vm.setFuelUse(fuel_usetest);
        assertEquals(fuel_usetest, vm.getFuelUse());
    }

    @Test
    void getDistance() {
        assertEquals(distance, vm.getDistance());
    }

    @Test
    void setDistance() {
        vm.setDistance(distancetest);
        assertEquals(distancetest, vm.getDistance());
    }
}