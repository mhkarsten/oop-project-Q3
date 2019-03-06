package client.foodAPI;

import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;

public class FoodAPI {

    static final String URL_RANDOMEAL = "https://www.themealdb.com/api/json/v1/1/random.php";
    static final String URL_SPESIFICMEAL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    static final String URL_CATEGORYMEAL = "https://www.themealdb.com/api/json/v1/1/filter.php?c={nameOfCategory";

    public HttpHeaders acceptHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }


    //Get random meal
    public Meal[] getRandomMeal() {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<Meal[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Meal[]> response = restTemplate.exchange(URL_RANDOMEAL,
            HttpMethod.GET, entity, Meal[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {
            Meal[] meal = response.getBody();

            if (meal != null) {

                return meal;
            } else {

                System.out.println("(Client Side) The returned meal does not exist.");
            }
        }

        return null;
    }

    //Get a specific meal
    public Meal[] getMeal(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<Meal[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Meal[]> response = restTemplate.exchange(URL_SPESIFICMEAL + mealName,
            HttpMethod.GET, entity, Meal[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            Meal[] meal = response.getBody();

            if (meal != null) {

                return meal;
            }
        }

         return null;
    }

    //Get all meals by category
    public ArrayList<Meal[]> getMealCategory(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_CATEGORYMEAL + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {


            JSONArray mealsObj = (JSONArray) response.getBody().get("meals");

            if (mealsObj != null) {

                Iterator mealItr = mealsObj.iterator();

                ArrayList<Meal[]> categoryMeals = new ArrayList<>();

                while (mealItr.hasNext()) {

                    ArrayList<String> tempMeal = ((ArrayList<String>) mealItr.next());

                    Meal[] newMeal = getMeal(tempMeal.get(1));

                    categoryMeals.add(newMeal);
                }

                return categoryMeals;
            }
        }

        return null;
    }

    //Get all meat meals
    public ArrayList<Meal[]> getAllMeatMeals() {

        ArrayList<Meal[]> meatMeals = new ArrayList<>();

        String[] meatCategories = {"Beef",	"Chicken", "Lamb", "Pork", 	"Seafood" };

        for (int i = 0; i < 5; i++) {

            meatMeals.addAll(getMealCategory(meatCategories[i]));
        }

        return  meatMeals;
    }
}
