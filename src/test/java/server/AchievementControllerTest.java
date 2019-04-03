package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hamcrest.collection.IsArrayContainingInAnyOrder;
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
import org.springframework.web.util.DefaultUriBuilderFactory;
import server.controller.UserController;
import server.model.Achievement;
import server.model.Feat;
import server.model.User;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AchievementControllerTest {
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        //Create a basic set of headers with a specification of the type of body sent to and expected from the server
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);
        User tom=new User("tom","123");
        headers.setBasicAuth("tom","123");
        entity = new HttpEntity<>(tom,headers);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
        restTemplate.postForObject(domain + "/auth/register", entity, User.class);
    }

    @Test
    public void getAchievementsTest() {

        Achievement a1 = restTemplate.postForObject("/achievements/1", entity, Achievement.class);
        Achievement a2 = restTemplate.postForObject("/achievements/2", entity, Achievement.class);
        Achievement a3 = restTemplate.postForObject("/achievements/3", entity, Achievement.class);
        Assertions.assertArrayEquals(restTemplate.postForObject("/achievements/", entity, Achievement[].class), new Achievement[] {a1, a2, a3});
    }

    @Test
    public void retrieveUserOneAchievements() {

        Achievement a1 = restTemplate.postForObject("/achievements/1", entity, Achievement.class);
        Achievement a2 = restTemplate.postForObject("/achievements/2", entity, Achievement.class);
        assertThat(restTemplate.postForObject("/users/1/achievements", entity, Achievement[].class), IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(a1, a2));
    }

    @Test
    public void retrieveAchievementMinusOne() {
        Assertions.assertNull(this.restTemplate.postForObject("/achievements/-1", entity, Achievement.class));
    }

    @Test
    public void retrieveAchievementsWrong() {
        Assertions.assertNull(this.restTemplate.postForObject("/achievements/ach1", entity, Achievement.class));
    }

    @Test
    public void retrieveUserMinusOneAchievements() {
        Assertions.assertNull(restTemplate.postForObject("/users/-1/achievements", entity, Achievement[].class));
    }
    @Test
    public void unlockAchievementUserOneTest()
    {
        //CREATE
        User user = new User("Usnavi","96000");
        entity = new HttpEntity<>(user, headers);
        URI usnaviLocation = restTemplate.postForLocation(domain + "/users/new", entity);
        //READ
        user = restTemplate.postForObject(usnaviLocation, new HttpEntity<>(headers), User.class);


        //Unlocking of achievement test:
        restTemplate.postForObject(domain + "/users/"+user.getID()+"/achievements/unlock/1", new HttpEntity<>(headers), User.class);
        restTemplate.postForObject(domain + "/users/"+user.getID()+"/achievements/unlock/3", new HttpEntity<>(headers), User.class);
        Achievement a1 = restTemplate.postForObject(domain + "/achievements/1", entity, Achievement.class);
        Achievement a3 = restTemplate.postForObject(domain + "/achievements/3", entity, Achievement.class);
        Achievement[] usnaviAch=restTemplate.postForObject(domain + "/users/"+user.getID()+"/achievements", entity, Achievement[].class);
        System.out.println("Number of achievements: "+usnaviAch.length);
        assertThat(usnaviAch, IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(a1,a3));

    }
}
