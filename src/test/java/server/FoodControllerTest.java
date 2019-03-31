package server;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import server.model.Meal;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodControllerTest {
    HttpHeaders headers;
    HttpEntity entity;
    String domain;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setup() {
        //Create a basic set of headers with a specification of the type of body sent to and expected from the server
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String username = "tom";
        String password = "123";
        //The basic authentication as it works right now, [user]:[password]
        byte[] encAuth = Base64.encodeBase64((username + ':' + password).getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encAuth));
        entity = new HttpEntity<>(headers);
        domain = "http://localhost:" + port;
    }

    @Test
    public void getRandomMeal() {
        Meal[] meal=restTemplate.postForObject(domain + "/randomMeal", entity, Meal[].class);
        Assertions.assertNotNull(meal);
    }

    @Test
    public void getMeal() {
    }

    @Test
    public void getMealCategory() {
    }

    @Test
    public void getMeatMeals() {
    }
}
