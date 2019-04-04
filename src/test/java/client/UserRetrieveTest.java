package client;

import client.Service.MyRestTemplate;
import client.Service.UrlEndPoints;
import client.Service.UserSession;
import client.model.User;
import client.retrive.UserRetrieve;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;
import server.TestTemplateConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class UserRetrieveTest {
    @LocalServerPort
    private int port;

    protected UserRetrieve userRetrieve;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.userRetrieve = new UserRetrieve();
        userRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() {
        ArrayList<User> users = this.userRetrieve.getUsers();

        assertNotNull(users);
    }

    @Test
    void getUser() {

    }

    @Test
    void addGenericFeat() {

    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserFollow() {
    }

    @Test
    void updateUserFollowing() {
    }
}