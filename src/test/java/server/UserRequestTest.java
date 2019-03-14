package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.HttpStatusCodeException;
import server.model.User;
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

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRequestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;

    /**
     * The setting up of the headers for the test.
     */
    @Before
    public void setup() {
        //Create a basic set of headers with a specification of the type of body sent to and expected from the server
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String username="tom";
        String password="123";
        //The basic authentication as it works right now, [user]:[password]
        byte[] encAuth = Base64.encodeBase64((username+':'+password).getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encAuth));
        entity = new HttpEntity<>(headers);
        domain = "http://localhost:" + port;
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
        Assertions.assertEquals("Alex", this.restTemplate.postForObject(domain + "/users/1", entity, User.class
        ).getName());
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
    public void updateUserMinusOne() {
        User doesNotExist = new User(-1, "Unicorn", 420);
        entity = new HttpEntity<>(doesNotExist, headers);
        restTemplate.put(domain + "/users/update/", entity);
        Assertions.assertNull(restTemplate.postForObject(domain + "/users/" + doesNotExist.getID(), entity, User.class));
    }

    @Test
    public void deleteUserMinusOne() {
        ResponseEntity<?> response = restTemplate.exchange(domain + "/users/-1", HttpMethod.DELETE, entity, String.class);
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void updateUserAndUndo()
    {
        User userOne=restTemplate.postForObject(domain+"/users/1",new HttpEntity<>(headers),User.class);

        //UPDATE
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
    public void fullCrudTest() {
        //CREATE
        //It really said "US Navy", but hey
        User user = new User(4, "Usnavi", 1000);
        entity = new HttpEntity<>(user, headers);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        URI usnaviLocation = restTemplate.postForLocation(domain + "/users/new", entity, User.class);
        //READ
        user=restTemplate.postForObject(usnaviLocation,new HttpEntity<>(headers),User.class);

        //UPDATE
        user.setName("Lin-Manuel");
        entity = new HttpEntity<>(user, headers);
        restTemplate.put(domain + "/users/update/", entity);
        User updatedUser = restTemplate.postForObject(domain + "/users/" + user.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertEquals(updatedUser, user);

        //DELETE
        ResponseEntity<?> response = restTemplate.exchange(domain + "/users/" + user.getID(), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        User returnedUser2 = restTemplate.postForObject(domain + "/users/" + user.getID(), new HttpEntity<>(headers), User.class);
        Assertions.assertNull(returnedUser2);
    }
}
