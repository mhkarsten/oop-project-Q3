package client.retrieve;

import client.model.Meal;
import client.service.MyRestTemplate;
import client.service.UrlEndPoints;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Food retrieve.
 */
public class FoodRetrieve extends BaseRetrieve {

    /**
     * Get random meal meal [ ].
     *
     * @return the meal [ ]
     */
    @SuppressWarnings("Duplicates")
    public  Meal getRandomMeal() {
        //GETS A RANDOM MEAL OFF OF THE SERVER SIDE api

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<Meal> entity = new HttpEntity<>(headers);

        ResponseEntity<Meal> response = restTemplate.exchange(UrlEndPoints.Food.URL_RANDOMMEAL,
            HttpMethod.POST, entity, Meal.class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            Meal randomMeal = response.getBody();

            if (randomMeal != null) {

                return randomMeal;
            } else {

                System.out.println("(Client Side) The specified meal was null or doesnt exist.");
            }
        }

        return null;
    }

    //    /**
    //     * Get meal meal [ ].
    //     *
    //     * @param mealName the meal name
    //     * @return the meal [ ]
    //     */
    //    @SuppressWarnings("Duplicates")
    //    public  Meal getMeal(String mealName) {
    //        //GETS A SPECIFIC MEAL FROM THE api ON THE SERVER
    //
    //        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
    //
    //        HttpEntity<Meal> entity = new HttpEntity<>(headers);
    //
    //        Object[] uriValues = new Object[] {mealName};
    //
    //        ResponseEntity<Meal> response = restTemplate.exchange(UrlEndPoints.Food.URL_MEAL,
    //            HttpMethod.POST, entity, Meal.class, uriValues);
    //
    //        HttpStatus statusCode = response.getStatusCode();
    //        System.out.println("(Client Side) The http status code is: " + statusCode);
    //
    //        if (statusCode == HttpStatus.OK) {
    //
    //            if (response.getBody() != null) {
    //
    //                return response.getBody();
    //            } else {
    //
    //                System.out.println("(Client Side) The specified meal was null or doesnt exist.");
    //            }
    //        }
    //
    //        return null;
    //    }

    /**
     * Gets meal category.
     *
     * @param category the category
     * @return the meal category
     */
    @SuppressWarnings("Duplicates")
    public  ArrayList<Meal> getMealCategory(String category ) {
        //GETS A SPECIFIC MEAL CATEGORY OFF OF THE SERVER SIDE api

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<Meal[]> entity = new HttpEntity<Meal[]>(headers);

        Object[] uriValues = new Object[] {category};

        ResponseEntity<Meal[]> response = restTemplate.exchange(UrlEndPoints.Food.URL_MEALCATEGORY,
            HttpMethod.POST, entity, Meal[].class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {

            Meal[] list = response.getBody();

            ArrayList<Meal> mealList = new ArrayList<Meal>();

            if (list != null) {

                mealList.addAll(Arrays.asList(list));

                return mealList;
            } else {

                System.out.println("(Client Side) Getting the desired meals failed, the response was null.");
            }
        }

        return null;
    }

    /**
     * Gets all meat meals.
     *
     * @return the all meat meals
     */
    @SuppressWarnings("Duplicates")
    public  ArrayList<Meal> getAllMeatMeals() {
        //GETS ALL OF THE MEALS WITH MEAT OFF OF THE SERVER api

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<Meal[]> entity = new HttpEntity<Meal[]>(headers);

        ResponseEntity<Meal[]> response = restTemplate.exchange(UrlEndPoints.Food.URL_MEATMEALs,
            HttpMethod.POST, entity, Meal[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {

            Meal[] list = response.getBody();

            ArrayList<Meal> mealList = new ArrayList<Meal>();

            if (list != null) {

                mealList.addAll(Arrays.asList(list));

                return mealList;
            } else {

                System.out.println("(Client Side) Getting the desired meals failed, the response was null.");
            }
        }

        return null;
    }
}
