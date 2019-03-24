package server.controller;

import server.model.Meal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import server.API.FoodAPI;

import java.util.List;
import java.util.Optional;

/**
 * The type Food controller.
 */
@RestController
public class FoodController {

    /**
     * Handle method argument type mismatch response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    //Handles bad requests
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets random meal.
     *
     * @return the random meal
     */
    //Sends a random meal to the client
    @RequestMapping
        (value = "/randomMeal",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Meal[]> getRandomMeal() {

        Optional<Meal[]> randomMeal = Optional.of(FoodAPI.getRandomMeal());
        return randomMeal.map(achievement -> new ResponseEntity<>(achievement, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets meal.
     *
     * @param mealName the meal name
     * @return the meal
     */
    //Sends a specific meal to the client
    @RequestMapping
        (value = "/meal/{mealName}",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Meal[]> getMeal(@PathVariable("mealName") String mealName) {

        Optional<Meal[]> meal = Optional.of(FoodAPI.getMeal(mealName));
        return meal.map(achievement -> new ResponseEntity<>(achievement, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets meal category.
     *
     * @param categoryName the category name
     * @return the meal category
     */
    //Sends a meal category to the client
    @RequestMapping
        (value = "/meals/{categoryName}",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Meal[]> getMealCategory(@PathVariable("categoryName") String categoryName) {

        List<Meal[]> meals = FoodAPI.getMealCategory(categoryName);

        if (!meals.isEmpty()) {

            return meals;
        } else {

            System.out.println("(Server Side) The category doesnt exist or is empty.");
            return null;
        }
    }

    /**
     * Gets meat meals.
     *
     * @return the meat meals
     */
    //Sends all the meat meals to the client
    @RequestMapping
        (value = {"/meals/meat"},
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Meal[]> getMeatMeals() {

        List<Meal[]> meatMeals = FoodAPI.getAllMeatMeals();

        if (!meatMeals.isEmpty()) {

            return meatMeals;
        } else  {

            System.out.println("(Server Side) Something went wrong, all meals with meat could not be accessed.");
            return null;
        }
    }
}
