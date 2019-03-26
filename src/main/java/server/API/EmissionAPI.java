package server.API;

import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import server.model.DietEmission;
import server.model.EnergyEmission;
import server.model.FlightEmission;
import server.model.VehicleEmission;

import java.util.ArrayList;

import static client.retrive.UserRetrieve.setAuthHeaders;
import static server.model.DietEmission.JSONtoDiet;
import static server.model.EnergyEmission.JSONtoEnergy;
import static server.model.FlightEmission.JSONtoFlight;
import static server.model.VehicleEmission.JSONtoVehicle;

/**
 * The type Emission api.
 */
public class EmissionAPI {
    private static final String KEY = "key=5a927d96eca397b6659a3c361ce32254&green_electricity=23&dishwasher_use=42&";
    private static final String URL_BASE = "http://impact.brighterplanet.com";
    private static final String URL_CAR = URL_BASE + "/automobile_trips.json?";
    private static final String URL_ENERGY = URL_BASE + "/residences.json?";
    private static final String URL_DIET = URL_BASE + "/diets.json?";
    private static final String URL_FLIGHT = URL_BASE + "/flights.json?";

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
                add(new ArrayList<Object>(){{add(distance); add(distanceString);}});
                add(new ArrayList<Object>(){{add(duration); add(durationString);}});
                add(new ArrayList<Object>(){{add(sizeClass); add(sizeClassString);}});
            }
        };

        StringBuilder keyString = new StringBuilder(KEY);

        for (int i = 0; i < 3; i++) {

            if (uriStrings.get(i).get(0) != null && uriStrings.get(i).get(0) != "0") {

                keyString.append(uriStrings.get(i).get(1));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_CAR,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return JSONtoVehicle(emissionResponse);
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
        String  naturalGasCostString = "&monthly_natural_gas_cost=" + naturalGasCost; //in USD per month

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>(){{add(dishwasherUse); add(dishwasherUseString);}});
                add(new ArrayList<Object>(){{add(airConditionerUse); add(airConditionerUseString);}});
                add(new ArrayList<Object>(){{add(greenEnergy); add(greenEnergyString);}});
                add(new ArrayList<Object>(){{add(naturalGasCost); add(naturalGasCostString);}});
            }
        };

        StringBuilder keyString = new StringBuilder(KEY);

        for (int i = 0; i < 4; i++) {

            if (uriStrings.get(i).get(0) != null) {
                if(uriStrings.get(i).get(0) != "0") {
                    keyString.append(uriStrings.get(i).get(1));
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_ENERGY,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return JSONtoEnergy(emissionResponse);
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
                add(new ArrayList<Object>(){{add(fishShare); add(fishShareString);}});
                add(new ArrayList<Object>(){{add(redMeatShare); add(redMeatShareString);}});
                add(new ArrayList<Object>(){{add(poultryShare); add(poultryShareString);}});
                add(new ArrayList<Object>(){{add(size); add(sizeString);}});
            }
        };

        StringBuilder keyString = new StringBuilder(KEY);
        keyString.append(startDate);
        keyString.append(endDate);

        for (int i = 0; i < 4; i++) {

            if (uriStrings.get(i).get(0) != null) {
                if(uriStrings.get(i).get(0) != "0") {
                    keyString.append(uriStrings.get(i).get(1));
                }
            }
        }

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_DIET,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return JSONtoDiet(emissionResponse);
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
        String startPortString ="&origin_airport=" + startPort; //a, airport
        String endPortString = "&destination_airport=" + endPort; //an airport

        ArrayList<ArrayList<Object>> uriStrings = new ArrayList<ArrayList<Object>>() {
            {
                add(new ArrayList<Object>(){{add(startPort); add(startPortString);}});
                add(new ArrayList<Object>(){{add(endPort); add(endPortString);}});
            }
        };

        StringBuilder keyString = new StringBuilder(KEY);

        for (int i = 0; i < 2; i++) {

            if (uriStrings.get(i).get(0) != null) {
                if(uriStrings.get(i).get(0) != "0") {
                    keyString.append(uriStrings.get(i).get(1));
                }
            } else {

                System.out.println("(Server Side) both a start and end destination are needed for an accurate.");
                return null;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<String> entity = new HttpEntity<>(keyString.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_FLIGHT,
            HttpMethod.POST, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            JSONObject emissionResponse = response.getBody();

            if (emissionResponse != null) {

                return JSONtoFlight(emissionResponse);
            }
        }

        System.out.println("(Client Side) Getting the desired emission failed or the object was null.");
        return null;
    }
}
