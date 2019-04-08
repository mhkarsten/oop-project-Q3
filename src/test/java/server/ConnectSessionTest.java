package server;

import client.model.User;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;

import org.springframework.web.util.DefaultUriBuilderFactory;
import static org.junit.Assert.*;

@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectSessionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    void setUp() {
        template.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }

    @Test
    void connectionTest() {
        assertEquals(template.getForEntity("/", String.class).getStatusCode(),HttpStatus.OK);
    }

    @Test
    void tryToGetUserTest() {
        assertEquals(template.getForEntity("/users/1", User.class).getStatusCode(),HttpStatus.OK);
    }

}
