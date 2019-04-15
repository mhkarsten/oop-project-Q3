package client.retrieve;

import client.model.EnergyEmission;
import client.model.FlightEmission;
import client.model.TrainEmission;
import client.model.VehicleEmission;
import client.service.MyRestTemplate;
import client.service.UrlEndPoints;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;

/**
 * The type Emissions retrieve.
 */
public class EmissionsRetrieve extends BaseRetrieve {

    /**
     * Gets vehicle emission.
     *
     * @param distance  the distance
     * @param duration  the duration
     * @param sizeClass the size class
     * @return the vehicle emission
     */
    public VehicleEmission getVehicleEmission(int distance, int duration, String sizeClass) {

        HashMap parameters = new HashMap();
        parameters.put("daily_distance", distance);
        parameters.put("daily_duration", duration);
        parameters.put("sizeClass", sizeClass);


        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        return restTemplate.postForObject(UrlEndPoints.Emission.URL_VEHICLE, requestBody, VehicleEmission.class);
    }

    /**
     * Gets flight emission.
     *
     * @param startingPort the starting port
     * @param endingPort   the ending port
     * @return the flight emission
     */
    public FlightEmission getFlightEmission(String startingPort, String endingPort) {

        HashMap parameters = new HashMap();
        parameters.put("startingPort", startingPort);
        parameters.put("endingPort", endingPort);


        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        return restTemplate.postForObject(UrlEndPoints.Emission.URL_FLIGHT, requestBody, FlightEmission.class);
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
    public EnergyEmission getEnergyEmission(int greenEnergy, String airConditionerUse, String dishwasherUse, int naturalGasCost) {
        HashMap parameters = new HashMap();
        parameters.put("greenEnergy", greenEnergy);
        parameters.put("airConditionerUse", airConditionerUse);
        parameters.put("dishwasherUse", dishwasherUse);
        parameters.put("naturalGasCost", naturalGasCost);


        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        return restTemplate.postForObject(UrlEndPoints.Emission.URL_ENERGY, requestBody, EnergyEmission.class);

    }

    //    /**
    //     * Gets diet emission.
    //     *
    //     * @param fishShare    the fish share
    //     * @param redMeatShare the red meat share
    //     * @param poultryShare the poultry share
    //     * @param size         the size
    //     * @return the diet emission
    //     */
    //    public DietEmission getDietEmission(float fishShare, float redMeatShare, float poultryShare, int size) {
    //        HashMap parameters = new HashMap();
    //        parameters.put("fishShare", fishShare);
    //        parameters.put("redMeatShare", redMeatShare);
    //        parameters.put("poultryShare", poultryShare);
    //        parameters.put("size", size);
    //
    //
    //        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
    //
    //        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);
    //
    //        DietEmission emission = restTemplate.postForObject(UrlEndPoints.Emission.URL_DIET, requestBody, DietEmission.class);
    //
    //        if (emission != null) {
    //
    //            return emission;
    //        }
    //
    //        System.out.println("(Client Side) The request was bad, the returned object was null.");
    //        return null;
    //}

    /**
     * Gets train emission.
     *
     * @param distance  the distance
     * @param duration  the duration
     * @return the train emission
     */
    public TrainEmission getTrainEmission(int distance, int duration) {

        HashMap parameters = new HashMap();
        parameters.put("distance", distance);
        parameters.put("duration", duration);

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);


        HttpEntity<HashMap> requestBody = new HttpEntity<HashMap>(parameters, headers);

        return restTemplate.postForObject(UrlEndPoints.Emission.URL_TRAIN, requestBody, TrainEmission.class);
    }
}
