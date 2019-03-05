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

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRequestTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    HttpEntity<User> entity;
    @Before
    public void setup()
    {
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("tom:123".getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encAuth));
        entity = new HttpEntity<>(headers);
    }
    @Test
    public void retrieveAllUsersSelectSecond() {
        Assertions.assertTrue(this.restTemplate.postForObject("http://localhost:" + port + "/users", entity,User[].class
                )[1].getUserName().equals("Jim")
        );
    }
    @Test
    public void retrieveUserOne() {
        Assertions.assertTrue(this.restTemplate.postForObject("http://localhost:" + port + "/user/1", entity,User.class
                ).getUserName().equals("Alex")
        );
    }
    /*
    A more verbose alternative. Useful for debugging and status checking
    @Test
    public void getUserOne()
    {
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:"+port+"/user/{account}",
                HttpMethod.POST, entity, User.class, "1");
        HttpStatus statusCode = response.getStatusCode();
        User specificUser=null;
        if (statusCode == HttpStatus.OK) {
            specificUser = response.getBody();
        }
        System.out.println("Status: "+statusCode.toString()+"\nbody:"+response.getBody());
        Assertions.assertNotNull(specificUser);

    }
    */
}
