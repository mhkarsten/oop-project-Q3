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
public class UserControllerTest {
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;
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
        us1 = restTemplate.postForObject(domain + "/users/1", entity, User.class);
        us2 = restTemplate.postForObject(domain + "/users/2", entity, User.class);
        us3 = restTemplate.postForObject(domain + "/users/3", entity, User.class);
    }

    @Test
    public void testConnection() {
        this.restTemplate.postForObject(domain + "/", entity, String.class);
    }

    @Test
    public void retrieveAllUsersSelectSecond() {
        Assertions.assertEquals("Jim", this.restTemplate.postForObject(domain + "/users", entity, User[].class)[1].getName());
    }

    @Test
    public void retrieveUserOne() {
        Assertions.assertEquals("Alex", this.restTemplate.postForObject(domain + "/users/1", entity, User.class).getName());
    }

    @Test
    public void retrieveUserOneWrong() {
        Assertions.assertNull(this.restTemplate.postForObject(domain + "/users/us1", entity, User.class));
    }

    @Test
    public void retrieveUserMinusOne() {
        Assertions.assertNull(this.restTemplate.postForObject(domain + "/users/-1", entity, User.class));
    }

    @Test
    public void getUsersThreeFollows() {
        Assertions.assertArrayEquals(restTemplate.postForObject(domain + "/users/3/following", entity, User[].class), new User[] {});
    }

    @Test
    public void getUsersFollowingThree() {
        assertThat(restTemplate.postForObject(domain + "/users/3/followers", entity, User[].class), IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(us1, us2));
        //Assertions.assertEquals(new HashSet<>(Arrays.asList(restTemplate.postForObject(domain+"/users/3/followers",entity,User[].class))), new HashSet<>(Arrays.asList({us1,us2})));
    }

    @Test
    public void updateUserMinusOne() {
        User doesNotExist = new User(-1, "Unicorn");
        entity = new HttpEntity<>(doesNotExist, headers);
        restTemplate.put(domain + "/users/update/", entity);
        Assertions.assertNull(restTemplate.postForObject(domain + "/users/" + doesNotExist.getID(), entity, User.class));
    }

    @Test
    public void deleteUserMinusOne() {
        ResponseEntity<?> response = restTemplate.exchange(domain + "/users/-1", HttpMethod.DELETE, entity, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void updateUserAndUndo() {
        User userOne = restTemplate.postForObject(domain + "/users/1", new HttpEntity<>(headers), User.class);

        userOne.setName("Jack");
        entity = new HttpEntity<>(userOne, headers);
        restTemplate.put(domain + "/users/update/", entity);
        User updatedUser = restTemplate.postForObject(domain + "/users/" + userOne.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertEquals(updatedUser, userOne);
        userOne.setName("Alex");
        restTemplate.put(domain + "/users/update/", entity);
        updatedUser = restTemplate.postForObject(domain + "/users/" + userOne.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertEquals(updatedUser, userOne);
    }
    @Test
    public void addFollowerDB() {
        User user = new User(22828, "Kamasi");
        entity = new HttpEntity<>(user, headers);
        URI usnaviLocation = restTemplate.postForLocation(domain + "/users/new", entity);
        user = restTemplate.postForObject(usnaviLocation, new HttpEntity<>(headers), User.class);

        restTemplate.postForObject(domain + "/users/"+user.getID()+"/follow/1", new HttpEntity<>(headers), User.class);
        restTemplate.postForObject(domain + "/users/"+user.getID()+"/follow/2", new HttpEntity<>(headers), User.class);
        User[] kamasiFollowsWho=restTemplate.postForObject(domain + "/users/"+user.getID()+"/following", entity, User[].class);
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
        User user1 = restTemplate.postForObject(domain + "/users/1", new HttpEntity<>(headers), User.class);

        URI featLocation = restTemplate.postForLocation(domain + "/users/1/feats/new", new HttpEntity<>(feat, headers));

        Feat retrievedFeat=restTemplate.postForObject(featLocation, new HttpEntity<>(headers), Feat.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(retrievedFeat));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        User changedUser1 = restTemplate.postForObject(domain + "/users/1", new HttpEntity<>(headers), User.class);

        Assertions.assertEquals(user1.getPoints()+150,changedUser1.getPoints());
    }
    @Test
    public void fullCrudTest() {
        //CREATE
        User user = new User(4, "Usnavi");
        entity = new HttpEntity<>(user, headers);
        URI usnaviLocation = restTemplate.postForLocation(domain + "/users/new", entity);
        //READ
        user = restTemplate.postForObject(usnaviLocation, new HttpEntity<>(headers), User.class);
        //UPDATE
        user.setName("Lin-Manuel");
        entity = new HttpEntity<>(user, headers);
        restTemplate.put(domain + "/users/update/", entity);
        User updatedUser = restTemplate.postForObject(domain + "/users/" + user.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertEquals(updatedUser, user);

        //DELETE
        ResponseEntity<?> response = restTemplate.exchange(domain + "/users/" + user.getID(), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        User returnedUser2 = restTemplate.postForObject(domain + "/users/" + user.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertNull(returnedUser2);
    }
}
