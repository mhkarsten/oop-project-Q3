package client.retrieve;

import client.model.Feat;
import client.service.MyRestTemplate;
import client.service.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;
import server.repository.FeatRepository;
import server.repository.UserRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class FeatRetriveTest {
    @LocalServerPort
    private int port;


    protected FeatRetrive featRetrive;

    @Autowired
    protected FeatRepository featRepository;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.featRetrive = new FeatRetrive();
        featRetrive.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserFeats() throws Exception {

        assertThrows(Exception.class, () -> { featRetrive.getUserFeats(1123L); });

        ArrayList<Feat> retrievedFeatures = featRetrive.getUserFeats(1L);

        assertNotNull(retrievedFeatures);
        assertEquals(3, retrievedFeatures.size());
    }
}