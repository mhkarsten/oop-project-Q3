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
public class UserControllerTest {
    User us1, us2, us3;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserController controller;

    /**
     * The setting up of the headers for the test.
     */
    @Test
    public void contextLoads() {
        Assertions.assertNotNull(controller);
    }

    @Before
    public void setup() {
        us1 = restTemplate.getForObject("/users/1", User.class);
        us2 = restTemplate.getForObject("/users/2", User.class);
        us3 = restTemplate.getForObject("/users/3", User.class);
    }

    @Test
    public void testConnection() {
        Assertions.assertEquals(restTemplate.getForEntity("/",String.class).getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void retrieveAllUsersSelectSecond() {
        Assertions.assertEquals("Jim", restTemplate.getForObject("/users", User[].class)[1].getName());
    }

    @Test
    public void retrieveUserOne() {
        Assertions.assertEquals("Alex", restTemplate.getForObject("/users/1", User.class).getName());
    }

    @Test
    public void retrieveUserOneWrong() {
        Assertions.assertEquals(restTemplate.getForEntity("/users/us1", User.class).getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void retrieveUserMinusOne() {
        Assertions.assertEquals(restTemplate.getForEntity("/users/-1", User.class).getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void getUsersThreeFollows() {
        Assertions.assertArrayEquals(restTemplate.getForObject("/users/3/following", User[].class), new User[] {});
    }

    @Test
    public void getUsersFollowingThree() {
        assertThat(restTemplate.getForObject("/users/3/followers", User[].class), IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(us1, us2));
        //Assertions.assertEquals(new HashSet<>(Arrays.asList(restTemplate.postForObject(domain+"/users/3/followers",entity,User[].class))), new HashSet<>(Arrays.asList({us1,us2})));
    }

    @Test
    public void updateUserMinusOne() {
        User doesNotExist = new User(-2, "Unicorn");
        restTemplate.put("/users/update/", new HttpEntity<>(doesNotExist));
        Assertions.assertEquals(restTemplate.getForEntity("/users/" + doesNotExist.getID(), User.class).getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteUserMinusOne() {
        ResponseEntity<?> response = restTemplate.exchange("/users/-1", HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders[]{}), String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void updateUserAndUndo() {
        us1.setName("Jack");
        restTemplate.put("/users/update/", new HttpEntity<>(us1));
        System.out.print(us1.getID());
        User updatedUser = restTemplate.getForObject("/users/1", User.class);
        Assertions.assertEquals(updatedUser, us1);
        us1.setName("Alex");
        restTemplate.put( "/users/update/", new HttpEntity<>(us1));
        updatedUser = restTemplate.getForObject( "/users/" + us1.getID(),   User.class);
        Assertions.assertEquals(updatedUser, us1);
    }
    @Test
    public void addFollowerDB() {
        User user = new User(22828, "Kamasi");
        URI usnaviLocation = restTemplate.postForLocation( "/users/new", new HttpEntity<>(user));
        user = restTemplate.getForObject(usnaviLocation,   User.class);

        restTemplate.getForEntity( "/users/"+user.getID()+"/follow/1",   User.class);
        restTemplate.getForEntity( "/users/"+user.getID()+"/follow/2",   User.class);
        User[] kamasiFollowsWho=restTemplate.getForObject( "/users/"+user.getID()+"/following", User[].class);
        System.out.println("Number of followers: "+kamasiFollowsWho.length);
        assertThat(kamasiFollowsWho, IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(us1,us2));
    }
    @Test
    public void setFollowingFollowersTest() {
        User user = new User(24601, "Jean Valjean");
        //These lines of code make me really sad...
        //Is there any way to neatly initialize a set?
        Set<User> following = new HashSet<>(Arrays.asList(new User[] {us1, us2}));
        Set<User> follower = new HashSet<>(Arrays.asList(new User[] {us2, us3}));
        user.setFollower(follower);
        user.setFollowing(following);
        Assertions.assertEquals(user.getFollowing(), following);
        Assertions.assertEquals(user.getFollowers(), follower);

    }
    @Test
    public void updateFeatsTest() {
        Feat feat = new Feat(1,150,4, new Date(),null);
        User user1 = restTemplate.getForObject( "/users/1",  User.class);

        URI featLocation = restTemplate.postForLocation( "/users/1/feats/new", new HttpEntity<>(feat));

        Feat retrievedFeat=restTemplate.getForObject(featLocation,   Feat.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(retrievedFeat));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        User changedUser1 = restTemplate.getForObject( "/users/1",  User.class);

        Assertions.assertEquals(user1.getPoints()+150,changedUser1.getPoints());
    }
    @Test
    public void fullCrudTest() {
        //CREATE
        User user = new User(4, "Usnavi");
        URI usnaviLocation = restTemplate.postForLocation( "/users/new", new HttpEntity<>(user));
        //READ
        user = restTemplate.getForObject(usnaviLocation,   User.class);
        //UPDATE
        user.setName("Lin-Manuel");
        restTemplate.put( "/users/update/", new HttpEntity<>(user));
        User updatedUser = restTemplate.getForObject( "/users/" + user.getID(),   User.class);
        Assertions.assertEquals(updatedUser, user);

        //DELETE
        ResponseEntity<?> response = restTemplate.exchange( "/users/" + user.getID(), HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders[]{}),  String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(restTemplate.getForEntity( "/users/" + user.getID(),   User.class).getStatusCode(),HttpStatus.NOT_FOUND);
    }
}
