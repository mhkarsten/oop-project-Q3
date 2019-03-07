package client.foodAPI;

import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class FoodAPI {

    static final String URL_RANDOMEAL = "https://www.themealdb.com/api/json/v1/1/random.php";
    static final String URL_SPESIFICMEAL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    static final String URL_CATEGORYMEAL = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";

    public static void main(String[] args) {

        System.out.println(getAllMeatMeals());
    }

    public static Meal[] JSONToMeal(JSONObject JSONMeal) {

        Object mealList = JSONMeal.get("meals");
        ArrayList<LinkedHashMap> meal = (ArrayList<LinkedHashMap>) mealList;
        LinkedHashMap LinkedMeal = meal.get(0);

        Meal newMeal = new Meal();

        newMeal.setStrMeal((String) LinkedMeal.get("strMeal"));
        newMeal.setIdMeal((String) LinkedMeal.get("idMeal"));
        newMeal.setStrArea((String) LinkedMeal.get("strArea"));
        newMeal.setStrCategory((String) LinkedMeal.get("strCategory"));
        newMeal.setStrMealThumb ((String) LinkedMeal.get("strMealThumb"));
        newMeal.setStrSource((String) LinkedMeal.get("strSource"));
        newMeal.setStrYoutube((String) LinkedMeal.get("strYoutube"));
        newMeal.setStrInstructions((String) LinkedMeal.get("strInstructions"));

        String tempTags = (String) LinkedMeal.get("strTags");

        if (tempTags != null) {

            String[] tempArray = tempTags.split(",");

            ArrayList<String> tempList = new ArrayList<>();
            tempList.addAll(Arrays.asList(tempArray));

            newMeal.setStrTags(tempList);
        }


        for (int i = 1; i < 15; i++) {

            ArrayList<String> tempIngredients = new ArrayList<>();

            tempIngredients.add((String) LinkedMeal.get("strIngredient" + i));

            newMeal.setStrIngredients(tempIngredients);
        }

        for (int i = 1; i < 15; i++) {

            ArrayList<String> tempMeasure = new ArrayList<>();

            tempMeasure.add((String) LinkedMeal.get("strMeasure" + i));

            newMeal.setStrMeasures(tempMeasure);
        }

        Meal[] encasedMeal = new Meal[1];
        encasedMeal[0] = newMeal;

        return encasedMeal;
    }

    //Creates JSON header for a GET request
    public static HttpHeaders acceptHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }


    //Get random meal
    public static Meal[] getRandomMeal() {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_RANDOMEAL,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {
            Meal[] meal = JSONToMeal(response.getBody());

            if (meal != null) {

                return meal;
            } else {

                System.out.println("(Client Side) The returned meal does not exist.");
            }
        }

        return null;
    }

    //Get a specific meal
    public static Meal[] getMeal(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_SPESIFICMEAL + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            Meal[] meal = JSONToMeal(response.getBody());

            if (meal != null) {

                return meal;
            }
        }

         return null;
    }

    //Get all meals by category
    public static ArrayList<Meal[]> getMealCategory(String mealName) {

        HttpHeaders headers = acceptHeaders();

        HttpEntity<JSONObject> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JSONObject> response = restTemplate.exchange(URL_CATEGORYMEAL + mealName,
            HttpMethod.GET, entity, JSONObject.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            ArrayList<Meal[]> categoryMeals = new ArrayList<>();

            Object mealList = response.getBody().get("meals");
            ArrayList<LinkedHashMap> meal = (ArrayList<LinkedHashMap>) mealList;

            for (int i = 0; i < meal.size(); i++) {

                LinkedHashMap LinkedMeal = meal.get(i);

                categoryMeals.add(getMeal((String) LinkedMeal.get("strMeal")));
            }

            return categoryMeals;
        }

        return null;
    }

    //Get all meat meals
    public static ArrayList<Meal[]> getAllMeatMeals() {

        ArrayList<Meal[]> meatMeals = new ArrayList<>();

        String[] meatCategories = {"Beef",	"Chicken", "Lamb", "Pork", 	"Seafood" };

        for (int i = 0; i < 5; i++) {

            meatMeals.addAll(getMealCategory(meatCategories[i]));
        }

        return meatMeals;
    }
}
