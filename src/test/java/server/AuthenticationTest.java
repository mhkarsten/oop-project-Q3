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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import server.model.User;

import java.nio.charset.Charset;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest {
    HttpHeaders headers;
    HttpEntity<User> entity;
    String domain;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
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
