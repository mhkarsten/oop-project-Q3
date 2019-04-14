package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
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
import server.model.Feat;
import server.model.User;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }

    @Test
    public void tryToLoginGood() {
        //Alex is already in the database
        User user = new User("Jim","s3cr3tc0de");
        Assertions.assertEquals(HttpStatus.OK,restTemplate.postForEntity( "/auth/login/Jim", new HttpEntity<>(user),User.class).getStatusCode());
    }
    @Test
    public void tryToLoginBad() {
        //Alex is already in the database
        User user = new User("Lubdubdub","secretcode");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,restTemplate.postForEntity( "/auth/login/Lubdubadubdub", new HttpEntity<>(user),User.class).getStatusCode());
    }
}
