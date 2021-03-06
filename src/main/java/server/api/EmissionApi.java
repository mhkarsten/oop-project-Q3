package server.api;

import static server.model.DietEmission.jsonToDiet;
import static server.model.EnergyEmission.jsonToEnergy;
import static server.model.FlightEmission.jsonToFlight;
import static server.model.TrainEmission.jsonToTrain;
import static server.model.VehicleEmission.jsonToVehicle;

import client.service.MyRestTemplate;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import server.model.DietEmission;
import server.model.EnergyEmission;
import server.model.FlightEmission;
import server.model.TrainEmission;
import server.model.VehicleEmission;

import java.util.ArrayList;

/**
 * The type Emission api.
 */
public class EmissionApi {

    /**
     * Method to build the key string for the headers.
     * @param uriStrings ArrayList of ArrayLists with Objects inside which are the parameters to add in the key
     * @return returns the StringBuilder keyString for use in the headers
     */
    public static StringBuilder keyStringBuilder(ArrayList<ArrayList<Object>> uriStrings) {
        StringBuilder keyString = new StringBuilder(ApiEndPoints.Emission.KEY);
        for (int i = 0; i < uriStrings.size(); i++) {
            if (uriStrings.get(i).get(0) != null && !uriStrings.get(i).get(0).equals(0)) {
                keyString.append(uriStrings.get(i).get(1));
            }
        }
        return keyString;

    }

    /**
     * Gets vehicle emission.
     *
     * @param distance  the distance
     * @param duration  the duration
     * @param sizeClass the size class
     * @return the vehicle emission
     */
    @SuppressWarnings("Duplicates")
    public static VehicleEmission getVehicleEmission(int distance, int duration, String sizeClass) {
        String distanceString = "&daily_distance=" + distance; //in kilometers
        String durationString = "&daily_duration=" + duration; //in seconds
        String sizeClassString = "&size_class=" + sizeClass; //a description (i.e. midsized)

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(distance);
                        add(distanceString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(duration);
                        add(durationString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(sizeClass);
                        add(sizeClassString);
                    }
                });
            }
        };

        StringBuilder keyString = keyStringBuilder(uriStrings);

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Emission.CAR,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return jsonToVehicle(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
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
    @SuppressWarnings("Duplicates")
    public static EnergyEmission getEnergyEmission(int greenEnergy, String airConditionerUse, String dishwasherUse, int naturalGasCost) {
        String dishwasherUseString = "&dishwasher_use=" + dishwasherUse; //in kwh (i think)
        String airConditionerUseString = "&air_conditioner_use=" + airConditionerUse; //a description
        String greenEnergyString = "&green_electricity=" + greenEnergy; //a description
        String naturalGasCostString = "&monthly_natural_gas_cost=" + naturalGasCost; //in USD per month

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(dishwasherUse);
                        add(dishwasherUseString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(airConditionerUse);
                        add(airConditionerUseString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(greenEnergy);
                        add(greenEnergyString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(naturalGasCost);
                        add(naturalGasCostString);
                    }
                });
            }
        };

        StringBuilder keyString = keyStringBuilder(uriStrings);

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Emission.ENERGY,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return jsonToEnergy(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
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
    @SuppressWarnings("Duplicates")
    public static DietEmission getDietEmission(float fishShare, float redMeatShare, float poultryShare, int size) {
        String startDate = "&start_date=2019-01-01"; //a date
        String endDate = "&end_date=2019-02-01"; //a date

        String fishShareString = "&fish_share=" + fishShare; //a decimal percentage
        String redMeatShareString = "&red_meat_share=" + redMeatShare; //a decimal percentage
        String poultryShareString = "&poultry_share=" + poultryShare; //a decimal percentage
        String sizeString = "&size=" + size; //a calorie value (i think)

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(fishShare);
                        add(fishShareString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(redMeatShare);
                        add(redMeatShareString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(poultryShare);
                        add(poultryShareString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(size);
                        add(sizeString);
                    }
                });
            }
        };

        StringBuilder keyString = keyStringBuilder(uriStrings);
        keyString.append(startDate);
        keyString.append(endDate);

        for (int i = 0; i < 4; i++) {

            if (uriStrings.get(i).get(0) != null && !uriStrings.get(i).get(0).equals(0)) {
                keyString.append(uriStrings.get(i).get(1));
            }
        }

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Emission.DIET,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return jsonToDiet(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
        return null;
    }

    /**
     * Gets flight emission.
     *
     * @param startPort the start port
     * @param endPort   the end port
     * @return the flight emission
     */
    @SuppressWarnings("Duplicates")
    public static FlightEmission getFlightEmission(String startPort, String endPort) {
        String startPortString = "&origin_airport=" + startPort; //a, airport
        String endPortString = "&destination_airport=" + endPort; //an airport

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(startPort);
                        add(startPortString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(endPort);
                        add(endPortString);
                    }
                });
            }
        };

        StringBuilder keyString;

        if (startPort != null && endPort != null) {
            keyString = keyStringBuilder(uriStrings);
        } else {
            System.out.println("(Server Side) both a start and end destination are needed for an accurate.");
            return null;
        }

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Emission.FLIGHT,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return jsonToFlight(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
        return null;
    }

    /**
     * Gets train emission.
     *
     * @param distance the distance
     * @param duration the duration
     * @return the train emission
     */
    @SuppressWarnings("Duplicates")
    public static TrainEmission getTrainEmission(int distance, int duration) {
        String distanceString = "&distance=" + distance; //in kilometers
        String durationString = "&duration=" + duration; //in seconds

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>() {
                    {
                        add(distance);
                        add(distanceString);
                    }
                });
                add(new ArrayList<Object>() {
                    {
                        add(duration);
                        add(durationString);
                    }
                });
            }
        };

        StringBuilder keyString = keyStringBuilder(uriStrings);

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        MyRestTemplate restTemplate = new MyRestTemplate();


        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Emission.TRAIN,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return jsonToTrain(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
        return null;
    }

}
