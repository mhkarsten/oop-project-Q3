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

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Food.getRandomMeal(),
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        Meal[] meal = jsonToMeal(response.getBody());
        return Optional.of(meal);
    }

    /** Method to return one specific meal from the DB.
     * @param mealName this parameter is the name of the meal that you would like to get from the database
     * @return This method will return one specific meal from the database.
     */
    public static Optional<Meal[]> getMeal(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Food.getSpecificMeal() + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);
        Meal[] meal = null;
        System.out.println(response.getBody().toString());
        if (response.getBody().get("meals") != null) {
            meal = jsonToMeal(response.getBody());
        }

        return Optional.ofNullable(meal);
    }

    /** Method to get meals from a certain category.
     * @param mealName This parameter is the name of the meal category that you would like returned.
     * @return This method returns an ArrayList of all meals in a specific category.
     */
    public static Optional<ArrayList<Meal[]>> getMealCategory(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(ApiEndPoints.Food.getCategoryMeal() + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);
        ArrayList<Meal[]> categoryMeals = null;
        if (response.getBody().get("meals") != null) {

            categoryMeals = new ArrayList<>();

            Object mealList = response.getBody().get("meals");
            ArrayList<LinkedHashMap> meal = (ArrayList<LinkedHashMap>) mealList;

            for (int i = 0; i < meal.size(); i++) {

                LinkedHashMap linkedMeal = meal.get(i);

                categoryMeals.add(getMeal((String) linkedMeal.get("strMeal")).get());
            }
        }

        return Optional.ofNullable(categoryMeals);
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
