package server;
import client.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
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
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRequestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;
    @Before
    public void setup()
    {
        headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("tom:123".getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encAuth));
        entity = new HttpEntity<>(headers);
        domain="http://localhost:" + port;
    }
    @Test
    public void testConnection()
    {
        Assertions.assertEquals(this.restTemplate.postForObject(domain+"/", entity,String.class
                ),"You are connected"
        );
    }
    @Test
    public void retrieveAllUsersSelectSecond() {
        Assertions.assertTrue(this.restTemplate.postForObject(domain+"/users", entity,User[].class
                )[1].getName().equals("Jim")
        );
    }
    @Test
    public void retrieveUserOne() {
        Assertions.assertTrue(this.restTemplate.postForObject(domain+"/user/1", entity,User.class
                ).getName().equals("Alex")
        );
    }
    @Test
    public void retrieveUserOneString() {
        Assertions.assertNull(this.restTemplate.postForObject(domain+"/user/us1", entity,User.class
                )
        );
    }
    @Test
    public void retrieveUserMinusOne() {
        Assertions.assertNull(this.restTemplate.postForObject(domain+"/user/-1", entity,User.class
                )
        );
    }
    @Test
    public void createCheckUser() {
        ObjectMapper mapper=new ObjectMapper();
        //It really said "US Navy", but hey
        User user =  new User(4,"Usnavi",1000);
        entity = new HttpEntity<>(user,headers);
        User returnedUser=this.restTemplate.postForObject(domain+ "/newUser", entity,User.class);
        try{
            System.out.println("Our user: "+mapper.writeValueAsString(user));
            System.out.println("The first returned user: "+mapper.writeValueAsString(returnedUser));
        } catch (JsonProcessingException exp)
        {
        }
        Assertions.assertEquals(user.getName(), returnedUser.getName());
    }
    @Test
    public void createCheckDeleteUser() {
        //It really said "US Navy", but hey
        User user =  new User(4,"Usnavi",1000);
        entity = new HttpEntity<>(user,headers);
        User returnedUser=restTemplate.postForObject(domain+"/newUser", entity,User.class);
        Assertions.assertEquals(user.getName(), returnedUser.getName());

        ResponseEntity<String> response = restTemplate.exchange(domain+"/user/"+returnedUser.getID(), HttpMethod.DELETE, entity, String.class);
        System.out.println("Response from DELETE: "+response.getBody());
        User returnedUser2=restTemplate.postForObject(domain+"/user/"+returnedUser.getID(), entity,User.class);
        try{
            ObjectMapper mapper=new ObjectMapper();
            System.out.println("Our user: "+mapper.writeValueAsString(user));
            System.out.println("The first returned user: "+mapper.writeValueAsString(returnedUser));
            System.out.println("The second returned user (should be null): "+mapper.writeValueAsString(returnedUser2));
        } catch (JsonProcessingException exp)
        {
        }
        Assertions.assertNull(returnedUser2);
    }
}
