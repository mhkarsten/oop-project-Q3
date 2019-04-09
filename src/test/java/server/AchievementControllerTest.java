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
import org.springframework.context.annotation.Import;
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
@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AchievementControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }

    @Test
    public void getAchievementsTest() {

        Achievement a1 = restTemplate.getForObject("/achievements/1", Achievement.class);
        Achievement a2 = restTemplate.getForObject("/achievements/2", Achievement.class);
        Achievement a3 = restTemplate.getForObject("/achievements/3", Achievement.class);
        Achievement a4 = restTemplate.getForObject("/achievements/4", Achievement.class);
        Assertions.assertArrayEquals(restTemplate.getForObject("/achievements/", Achievement[].class), new Achievement[] {a1, a2, a3, a4});
    }

    @Test
    public void retrieveUserOneAchievements() {

        Achievement a1 = restTemplate.getForObject("/achievements/1", Achievement.class);
        Achievement a2 = restTemplate.getForObject("/achievements/2", Achievement.class);
        assertThat(restTemplate.getForObject("/users/1/achievements", Achievement[].class), IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(a1, a2));
    }

    @Test
    public void retrieveAchievementMinusOne() {
        Assertions.assertEquals(this.restTemplate.getForEntity("/achievements/-1", Achievement.class).getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void retrieveAchievementsWrong() {
        Assertions.assertEquals(this.restTemplate.getForEntity("/achievements/ach1", Achievement.class).getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void retrieveUserMinusOneAchievements() {
        Assertions.assertEquals(restTemplate.getForEntity("/users/-1/achievements", Achievement[].class).getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void unlockAchievementUserOneTest()
    {
        //CREATE
        User user = new User("Usnavi","96000");
        user = restTemplate.postForObject("/auth/register", new HttpEntity<>(user),User.class);
        //Unlocking of achievement test:
        restTemplate.getForEntity("/users/"+user.getID()+"/achievements/unlock/1", User.class);
        restTemplate.getForEntity("/users/"+user.getID()+"/achievements/unlock/3", User.class);
        Achievement a1 = restTemplate.getForObject("/achievements/1", Achievement.class);
        Achievement a3 = restTemplate.getForObject("/achievements/3", Achievement.class);
        Achievement[] usnaviAch=restTemplate.getForObject("/users/"+user.getID()+"/achievements", Achievement[].class);
        System.out.println("Number of achievements: "+usnaviAch.length);
        assertThat(usnaviAch, IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(a1,a3));

    }
}
