package client.retrieve;

import client.Service.MyRestTemplate;
import client.Service.UserSession;
import client.model.Meal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class FoodRetrieveTest {
    @LocalServerPort
    private int port;

    private FoodRetrieve foodRetrieve;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.foodRetrieve = new FoodRetrieve();
        foodRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRandomMeal() {
        Meal fetchedRandomMeal = this.foodRetrieve.getRandomMeal();

        assertNotNull(fetchedRandomMeal);
    }


    @Test
    void getMealCategory() {
        ArrayList<Meal> veganMeals = this.foodRetrieve.getMealCategory("vegan");
        assertNotNull(veganMeals);

        assertEquals("vegan", veganMeals.get(0).getStrCategory().toLowerCase());
    }

    @Test
    void getAllMeatMeals() {
        ArrayList<Meal> meatMeals = this.foodRetrieve.getAllMeatMeals();
        assertNotNull(meatMeals);

        String[] categories = {"Beef", "Chicken", "Lamb", "Pork", "Seafood"};
        assertTrue(Arrays.asList(categories).contains(meatMeals.get(0).getStrCategory()));
    }
}