package client.service;

import client.model.Achievement;
import client.model.User;
import client.retrieve.AchievementRetrieve;
import client.retrieve.UserRetrieve;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;
import server.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class AchievementGeneratorTest {
    @LocalServerPort
    private int port;


    protected UserRetrieve userRetrieve;
    protected AchievementRetrieve achievementRetrieve;
    @Autowired
    protected UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.userRetrieve = new UserRetrieve();
        userRetrieve.setRestTemplate(restTemplate);

        this.achievementRetrieve = new AchievementRetrieve();
        this.achievementRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
        UserSession.getInstance().cleanUserSession();
    }

    @Test
    void giveUserAch() {
        User[] users = this.userRetrieve.getUser(1L);
        assertNotNull(users[0]);


        User user = users[0];
        UserSession.getInstance().setCurrentUser(user);

        assertTrue(user.equals(UserSession.getInstance().getCurrentUser()));
        AchievementGenerator.setAchievementRetrieve(this.achievementRetrieve);

        Achievement achievement = AchievementGenerator.giveUserAch(user);
        assertNotNull(achievement);
    }

    @Test
    void progressivePointAchievement() {

    }

    @Test
    void foodAchievement() {
        assertNull(AchievementGenerator.foodAchievement());
    }

    @Test
    void transportAchievement() {
        assertNull(AchievementGenerator.transportAchievement());
    }

    @Test
    void energyAchievement() {
        assertNull(AchievementGenerator.energyAchievement());
    }

    @Test
    void achievementLabelsTest() {
        assertEquals("file:images/bulbbadge.jpg", AchievementGenerator.bulBadgepath);
        assertEquals("file:images/batterybadge.jpg", AchievementGenerator.batteryBadgePath);
        assertEquals("file:images/candlebadge.jpg", AchievementGenerator.candleBadge);
        assertEquals("file:images/cactusbadge.jpg", AchievementGenerator.cactusBadge);
        assertEquals("file:images/fishbadge.jpg", AchievementGenerator.fishBadge);
        assertEquals("file:images/powerplantbadge.jpg", AchievementGenerator.powerPlantBadge);
        assertEquals("file:images/treehousebadge.jpg", AchievementGenerator.treeHouseBadge);
        assertEquals("file:images/truckbadge.jpg", AchievementGenerator.truckBadge);
        assertEquals("file:images/plugbadge.jpg", AchievementGenerator.plugBadge);
        assertEquals("file:images/worldbadge.jpg", AchievementGenerator.worldBadge);
    }
}