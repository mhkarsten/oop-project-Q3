package client.retrieve;

import client.service.MyRestTemplate;
import client.service.UserSession;

import client.model.EnergyEmission;
import client.model.FlightEmission;
import client.model.TrainEmission;
import client.model.VehicleEmission;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class EmissionsRetrieveTest {

    @LocalServerPort
    private int port;

    protected EmissionsRetrieve emissionsRetrieve;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.emissionsRetrieve = new EmissionsRetrieve();
        emissionsRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getVehicleEmission() {
        int distance = 120;
        int duration = 60*60;
        String sizeClass = "small";

        VehicleEmission vehicleEmission = this.emissionsRetrieve.getVehicleEmission(distance, duration, sizeClass);
        assertNotNull(vehicleEmission);
        assertNotNull(vehicleEmission.getCarbon());
    }

    @Test
    void getFlightEmission() {
        String startingPoint = "AMS";
        String endPoint = "DET";

        FlightEmission flightEmission = this.emissionsRetrieve.getFlightEmission(startingPoint, endPoint);
        assertNotNull(flightEmission.getFuelUse());
        assertNotNull(flightEmission.getCarbon());
    }

    @Test
    void getEnergyEmission() {
        int greenEnergy = 100;
        String airConditionerUse = "100";
        String dishwasherUse = "100";
        int naturalGasCost = 100;

        EnergyEmission energyEmission = this.emissionsRetrieve.getEnergyEmission(greenEnergy, airConditionerUse, dishwasherUse, naturalGasCost);
        assertNotNull(energyEmission);
        assertNotNull(energyEmission.getCarbon());
    }

//    @Test
//    void getDietEmission() {
//        float fishShare = 25;
//        float redMeatShare = 25;
//        float poultryShare = 25;
//        int size = 10;
//
//        DietEmission dietEmission = this.emissionsRetrieve.getDietEmission(fishShare, redMeatShare, poultryShare, size);
//        assertNotNull(dietEmission);
//        assertNotNull(dietEmission.getCarbon());
//    }

    @Test
    void getTrainEmission() {
        int duration = 100;
        int distance = 100;

        TrainEmission trainEmission = this.emissionsRetrieve.getTrainEmission(duration, distance);
        assertNotNull(trainEmission);
        assertNotNull(trainEmission.getCarbon());
    }
}