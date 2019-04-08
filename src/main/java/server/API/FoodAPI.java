package server.API;

import server.model.Meal;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
;
import static server.model.Meal.JSONToMeal;

/**
 * This class is the integration of TheFoodDB api. This is an online database of various meals which
 * contains various information on each item of food, such as ingredients, and instructions on how
 * to make them. These are given as JSON objects, and are then converted to a meal object which
 * can be displayed in various ways.
 */
public class FoodAPI {

    static final String URL_RANDOMEAL = "https://www.themealdb.com/api/json/v1/1/random.php";
    static final String URL_SPESIFICMEAL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    static final String URL_CATEGORYMEAL = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";

    //Creates JSON header for a GET request
    public static HttpHeaders acceptHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    /**
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
            meal = JSONToMeal(response.getBody());
        }

        return Optional.of(meal);
    }

    /**
     * @return This method will return one specific meal from the database.
     * @param mealName
     * This parameter is the name of the meal that you would like to get from the database
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
            meal = JSONToMeal(response.getBody());
        }

        return Optional.of(meal);
    }

    /**
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

                LinkedHashMap LinkedMeal = meal.get(i);

                categoryMeals.add(getMeal((String) LinkedMeal.get("strMeal")).get());
            }
        }

        return Optional.of(categoryMeals);
    }

    /**
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
