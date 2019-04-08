package client.retrieve;

import client.service.MyRestTemplate;
import client.service.UserSession;
import client.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;
import server.model.Feat;
import server.repository.UserRepository;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class UserRetrieveTest {
    @LocalServerPort
    private int port;

    protected UserRetrieve userRetrieve;
    @Autowired
    protected UserRepository userRepository;

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
        User[] user = this.userRetrieve.getUser(1);
        assertNotNull(user);
        assertNotNull(user[0]);
        assertEquals("Alex", user[0].getName());
        assertEquals(1, user[0].getID());

        User[] userShouldNotExist = this.userRetrieve.getUser(112);
        assertNull(userShouldNotExist[0]);
    }

    @Test
    void addGenericFeat() {
        Set<Feat> featsUserOne = this.userRepository.findById(1L).get().getFeats();
        int totalAmountFeats = featsUserOne.size();
        double totalAmountPoints = featsUserOne.stream().mapToDouble(Feat::getPoints).sum();

        this.userRetrieve.addGenericFeat(1L, 100);

        featsUserOne = this.userRepository.findById(1L).get().getFeats();
        assertEquals(totalAmountFeats + 1, featsUserOne.size());
        assertEquals(totalAmountPoints + 100, featsUserOne.stream().mapToDouble(Feat::getPoints).sum());
    }

    @Test
    void getUserFollow() {
        Set<User> followers = this.userRetrieve.getUserFollow(true, 1L);
        assertNotNull(followers);
        assertEquals(1, followers.size());

        Set<User> following = this.userRetrieve.getUserFollow(false, 1L);
        assertNotNull(following);
    }

    @Test
    void updateUserFollowing() {
        Set<User> following = this.userRetrieve.getUserFollow(false, 1L);
        assertNotNull(following);
        assertEquals(2, following.size());

        this.userRetrieve.updateUserFollowing(1L, 4L);

        following = this.userRetrieve.getUserFollow(false, 1L);
        assertNotNull(following);
        assertEquals(3, following.size());
    }

    @Test
    void getUserByName() {
        User user = this.userRetrieve.getUserByName("Alex");

        assertNotNull(user);
        assertEquals("Alex", user.getName());

    }
}