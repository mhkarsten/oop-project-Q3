package server.api;

import static server.model.Meal.jsonToMeal;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import server.model.Meal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;


/**
 * This class is the integration of TheFoodDB api. This is an online database of various meals which
 * contains various information on each item of food, such as ingredients, and instructions on how
 * to make them. These are given as JSON objects, and are then converted to a meal object which
 * can be displayed in various ways.
 */
public class FoodApi {

    static final String URL_RANDOMEAL = "https://www.themealdb.com/api/json/v1/1/random.php";
    static final String URL_SPESIFICMEAL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    static final String URL_CATEGORYMEAL = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";

    /**
     * Creates JSON header for a GET request.
     * @return Returns headers saying what this server accepts from and sends to the meal API
     */
    public static HttpHeaders acceptHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    /** Method to get a random meal from the DB.
     * @return This method returns a random meal from TheMealDB
     */
    public static Optional<Meal[]> getRandomMeal() {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_RANDOMEAL,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        Meal[] meal = null;
        if (statusCode == HttpStatus.OK) {
            meal = jsonToMeal(response.getBody());
        }

        return Optional.of(meal);
    }

    /** Method to return one specific meal from the DB.
     * @param mealName this parameter is the name of the meal that you would like to get from the database
     * @return This method will return one specific meal from the database.
     */
    @SuppressWarnings("Duplicates")
    public static Optional<Meal[]> getMeal(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_SPESIFICMEAL + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);
        Meal[] meal = null;
        if (statusCode == HttpStatus.OK) {
            meal = jsonToMeal(response.getBody());
        }

        return Optional.of(meal);
    }

    /** Method to get meals from a certain category.
     * @param mealName This parameter is the name of the meal category that you would like returned.
     * @return This method returns an ArrayList of all meals in a specific category.
     */
    @SuppressWarnings("Duplicates")
    public static Optional<ArrayList<Meal[]>> getMealCategory(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_CATEGORYMEAL + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);
        ArrayList<Meal[]> categoryMeals = null;
        if (statusCode == HttpStatus.OK) {

            categoryMeals = new ArrayList<>();

            Object mealList = response.getBody().get("meals");
            ArrayList<LinkedHashMap> meal = (ArrayList<LinkedHashMap>) mealList;

            for (int i = 0; i < meal.size(); i++) {

                LinkedHashMap linkedMeal = meal.get(i);

                categoryMeals.add(getMeal((String) linkedMeal.get("strMeal")).get());
            }
        }

        return Optional.of(categoryMeals);
    }

    /** Method to get all meals in the meat category.
     * @return This method returns a list of all of the meals that use meat.
     */
    public static ArrayList<Meal[]> getAllMeatMeals() {

        ArrayList<Meal[]> meatMeals = new ArrayList<>();

        String[] meatCategories = {"Beef", "Chicken", "Lamb", "Pork", "Seafood"};

        for (int i = 0; i < 5; i++) {

            meatMeals.addAll(getMealCategory(meatCategories[i]).get());
        }

        return meatMeals;
    }
}
