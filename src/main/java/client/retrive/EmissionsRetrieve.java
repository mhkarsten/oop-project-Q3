package client.retrive;

import client.Service.MyRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import server.model.DietEmission;
import server.model.EnergyEmission;
import server.model.FlightEmission;
import server.model.VehicleEmission;

import java.util.HashMap;

/**
 * The type Emissions retrieve.
 */
public class EmissionsRetrieve {
    private static final String URL_BASE = "http://localhost:8090";
    private static final String URL_VEHICLE = URL_BASE + "/vehicleEmission";
    private static final String URL_DIET =  URL_BASE + "/dietEmission";
    private static final String URL_FLIGHT = URL_BASE + "/flightEmission";
    private static final String URL_ENERGY = URL_BASE + "/energyEmission";

    /**
     * Gets vehicle emission.
     *
     * @param distance  the distance
     * @param duration  the duration
     * @param sizeClass the size class
     * @return the vehicle emission
     */
    public static VehicleEmission getVehicleEmission(int distance, int duration, String sizeClass) {

        HashMap parameters = new HashMap();
        parameters.put("distance", distance);
        parameters.put("duration", duration);
        parameters.put("sizeClass", sizeClass);

        MyRestTemplate restTemplate = new MyRestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        VehicleEmission emission = restTemplate.postForObject(URL_VEHICLE, requestBody, VehicleEmission.class);

        if (emission != null) {

            return emission;
        }

        System.out.println("(Client Side) The request was bad, the returned object was null.");
        return null;
    }

    /**
     * Gets flight emission.
     *
     * @param startingPort the starting port
     * @param endingPort   the ending port
     * @return the flight emission
     */
    public static FlightEmission getFlightEmission(String startingPort, String endingPort) {

        HashMap parameters = new HashMap();
        parameters.put("startingPort", startingPort);
        parameters.put("endingPort", endingPort);

        MyRestTemplate restTemplate = new MyRestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        FlightEmission emission = restTemplate.postForObject(URL_FLIGHT, requestBody, FlightEmission.class);

        if (emission != null) {

            return emission;
        }

        System.out.println("(Client Side) The request was bad, the returned object was null.");
        return null;
    }

    /**
     * Gets energy emission.
     *
     * @param greenEnergy       the green energy
     * @param airConditionerUse the air conditioner use
     * @param dishwasherUse     the dishwasher use
     * @param naturalGasCost    the natural gas cost
     * @return the energy emission
     */
    public static EnergyEmission getEnergyEmission(int greenEnergy, String airConditionerUse, String dishwasherUse, int naturalGasCost) {
        HashMap parameters = new HashMap();
        parameters.put("greenEnergy", greenEnergy);
        parameters.put("airConditionerUse", airConditionerUse);
        parameters.put("dishwasherUse", dishwasherUse);
        parameters.put("naturalGasCost", naturalGasCost);

        MyRestTemplate restTemplate = new MyRestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        EnergyEmission emission = restTemplate.postForObject(URL_ENERGY, requestBody, EnergyEmission.class);

        if (emission != null) {

            return emission;
        }

        System.out.println("(Client Side) The request was bad, the returned object was null.");
        return null;
    }

    /**
     * Gets diet emission.
     *
     * @param fishShare    the fish share
     * @param redMeatShare the red meat share
     * @param poultryShare the poultry share
     * @param size         the size
     * @return the diet emission
     */
    public static DietEmission getDietEmission(float fishShare, float redMeatShare, float poultryShare, int size) {
        HashMap parameters = new HashMap();
        parameters.put("fishShare", fishShare);
        parameters.put("redMeatShare", redMeatShare);
        parameters.put("poultryShare", poultryShare);
        parameters.put("size", size);

        MyRestTemplate restTemplate = new MyRestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        DietEmission emission = restTemplate.postForObject(URL_DIET, requestBody, DietEmission.class);

        if (emission != null) {

            return emission;
        }

        System.out.println("(Client Side) The request was bad, the returned object was null.");
        return null;
    }
}
