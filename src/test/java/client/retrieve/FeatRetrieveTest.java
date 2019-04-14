package client.retrieve;

import client.model.Feat;
import client.service.MyRestTemplate;
import client.service.UserSession;
import client.model.Meal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import server.SpringBoot;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class FeatRetrieveTest {
    @LocalServerPort
    private int port;

    private FeatRetrieve featRetrieve;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.featRetrieve = new FeatRetrieve();
        featRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserFeats() {
        ArrayList<Feat> fetchedRandomMeal = this.featRetrieve.getUserFeats(1);
        assertNotNull(fetchedRandomMeal);
    }
    @Test
    void getUserFeatsWrong() {
        assertNull(this.featRetrieve.getUserFeats(-1));
    }
}