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
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Food controller.
 */
@RestController
public class FoodController {
    /**
     * Gets random meal.
     *
     * @return the random meal
     */
    @RequestMapping
        (value = "/randomMeal",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Meal[] getRandomMeal() {
        return FoodAPI.getRandomMeal().get();
    }

    /**
     * Gets meal.
     *
     * @param mealName the meal name
     * @return the meal
     */
    @RequestMapping
        (value = "/meal/{mealName}",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Meal[] getMeal(@PathVariable("mealName") String mealName) {
        return FoodAPI.getMeal(mealName).get();
    }

    /**
     * Gets meal category.
     *
     * @param categoryName the category name
     * @return the meal category
     */
    @RequestMapping
        (value = "/meals/{categoryName}",
            method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Meal[]> getMealCategory(@PathVariable("categoryName") String categoryName) {

        List<Meal[]> meals = FoodAPI.getMealCategory(categoryName).get();

        if (!meals.isEmpty()) {
            return meals;
        } else {
            throw new NoSuchElementException("(Server Side) The category doesnt exist or is empty.");
        }
    }

    /**
     * Gets meat meals.
     *
     * @return the meat meals
     */
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
            throw new NoSuchElementException("(Server Side) The category doesnt exist or is empty.");
        }
    }
}
