package server;

import client.User;
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
import org.springframework.web.client.ResourceAccessException;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRequestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;

    @Before
    public void setup() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("tom:123".getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encAuth));
        entity = new HttpEntity<>(headers);
        domain = "http://localhost:" + port;
    }

    public boolean tryToConnect(HttpHeaders h) {
        boolean connectionSuccess = false;
        try {
            this.restTemplate.postForObject(domain + "/", new HttpEntity<>(h), String.class);
            connectionSuccess = true;
        } catch (ResourceAccessException excp) {
        }
        return connectionSuccess;
    }

    @Test
    public void connectNoAuthHeaders() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectAllWrong() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("gandalf:mellon".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectWrongUser() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("gandalf:123".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectWrongPassword() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("tom:mellon".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void testConnection() {
        Assertions.assertEquals(this.restTemplate.postForObject(domain + "/", entity, String.class), "You are connected");
    }

    @Test
    public void retrieveAllUsersSelectSecond() {
        Assertions.assertEquals("Jim", this.restTemplate.postForObject(domain + "/users", entity, User[].class)[1].getName());
    }

    @Test
    public void retrieveUserOne() {
        Assertions.assertEquals("Alex", this.restTemplate.postForObject(domain + "/user/1", entity, User.class
        ).getName());
    }

    @Test
    public void retrieveUserOneWrong() {
        Assertions.assertNull(this.restTemplate.postForObject(domain + "/user/us1", entity, User.class));
    }

    @Test
    public void retrieveUserMinusOne() {
        Assertions.assertNull(this.restTemplate.postForObject(domain + "/user/-1", entity, User.class));
    }

    @Test
    public void updateUserMinusOne() {
        User doesNotExist = new User(-1, "Unicorn", 420);
        entity = new HttpEntity<>(doesNotExist, headers);
        restTemplate.put(domain + "/userUpdate/", entity);
        Assertions.assertNull(restTemplate.postForObject(domain + "/user/" + doesNotExist.getID(), entity, User.class));
    }

    @Test
    public void deleteUserMinusOne() {
        ResponseEntity<String> response = restTemplate.exchange(domain + "/user/-1", HttpMethod.DELETE, entity, String.class);
        Assertions.assertEquals(response.getBody(), "Could not delete user -1");
    }

    @Test
    public void fullCrudTest() {
        //CREATE
        //It really said "US Navy", but hey
        User user = new User(4, "Usnavi", 1000);
        entity = new HttpEntity<>(user, headers);

        //READ
        User returnedUser = restTemplate.postForObject(domain + "/newUser", entity, User.class);
        Assertions.assertEquals(user.getName(), returnedUser.getName());

        //UPDATE
        returnedUser.setName("Lin-Manuel");
        entity = new HttpEntity<>(returnedUser, headers);
        restTemplate.put(domain + "/userUpdate/", entity);
        User updatedUser = restTemplate.postForObject(domain + "/user/" + returnedUser.getID(), entity, User.class);
        Assertions.assertEquals(updatedUser, returnedUser);

        //DELETE
        ResponseEntity<String> response = restTemplate.exchange(domain + "/user/" + returnedUser.getID(), HttpMethod.DELETE, entity, String.class);
        System.out.println("Response from DELETE: " + response.getBody());
        User returnedUser2 = restTemplate.postForObject(domain + "/user/" + returnedUser.getID(), entity, User.class);
        Assertions.assertNull(returnedUser2);
    }
}