package server;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;
import server.model.User;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }

    public boolean tryToConnect(HttpHeaders h) {
        return HttpStatus.OK.equals(restTemplate.postForEntity( "/feats", new HttpEntity<>(h), String.class).getStatusCode());
    }
    @Test
    public void tryToRegisterExisting() {
        //Alex is already in the database
        User user = new User("Alex","MyNameIsAlexToo");
        Assertions.assertEquals(restTemplate.postForEntity( "/auth/register", new HttpEntity<>(user),User.class).getStatusCode(),HttpStatus.BAD_REQUEST);
    }
    @Test
    public void connectNoAuthHeaders() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectAllWrong() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("gandalf:mellon".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectWrongUser() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("gandalf:123".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }

    @Test
    public void connectWrongPassword() {
        HttpHeaders noPwdHeaders = new HttpHeaders();
        noPwdHeaders.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        noPwdHeaders.setContentType(MediaType.APPLICATION_JSON);
        byte[] encAuth = Base64.encodeBase64("tom:mellon".getBytes(Charset.forName("US-ASCII")));
        noPwdHeaders.set("Authorization", "Basic " + new String(encAuth));
        Assertions.assertFalse(tryToConnect(noPwdHeaders));
    }
}
