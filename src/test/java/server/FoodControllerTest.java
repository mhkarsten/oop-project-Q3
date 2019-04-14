package server;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import server.model.Meal;

@RunWith(SpringRunner.class)
@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setup() {
    }

    @Test
    public void getRandomMeal() {
        Meal meal=restTemplate.getForObject( "/randomMeal", Meal.class);
        Assertions.assertNotNull(meal);
    }

    @Test
    public void getMeal() {
        Meal meal=restTemplate.getForObject( "/meal/Arrabiata", Meal[].class)[0];
        Assertions.assertEquals("Spicy Arrabiata Penne",meal.getStrMeal());
    }
    @Test
    public void getMealWrong() {
        Assertions.assertNull(restTemplate.getForObject( "/meal/thisisnotameal", Meal[].class));
    }
    @Test
    public void getCategoryWrong() {
        Assertions.assertNull(restTemplate.getForObject( "/meals/thisisnotacategory", Meal[].class));
    }

    @Test
    public void getMealCategory() {
    }

    @Test
    public void getMeatMeals() {
    }
}
