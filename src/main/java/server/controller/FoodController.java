package server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.API.FoodApi;
import server.model.Meal;

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
    public Meal getRandomMeal() {

        Optional<Meal[]> randomMeal = FoodApi.getRandomMeal();
        if (randomMeal.isPresent()) {

            return randomMeal.get()[0];
        }
        return null;
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
        return FoodApi.getMeal(mealName).get();
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
        return FoodApi.getMealCategory(categoryName).get();
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

        List<Meal[]> meatMeals = FoodApi.getAllMeatMeals();

        if (!meatMeals.isEmpty()) {

            return meatMeals;
        } else  {
            throw new NoSuchElementException("(Server Side) The category doesnt exist or is empty.");
        }
    }
}
