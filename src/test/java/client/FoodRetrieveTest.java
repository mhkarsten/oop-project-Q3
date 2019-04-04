package client;

import client.model.Meal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static client.retrieve.FoodRetrieve.getRandomMeal;


class FoodRetrieveTest {

    //@Test
    void getRandomMealTest() {

        Meal randomMeal = getRandomMeal();

        Assertions.assertNotNull(randomMeal);
    }

    @Test
    void getMeal() {

    }

    @Test
    void getMealCategory() {

    }

    @Test
    void getAllMeatMeals() {

    }
}