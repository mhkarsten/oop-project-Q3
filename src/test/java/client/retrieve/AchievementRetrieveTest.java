package client.retrieve;

import client.model.Achievement;
import client.service.MyRestTemplate;
import client.service.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import server.SpringBoot;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBoot.class)
class AchievementRetrieveTest {
    @LocalServerPort
    private int port;

    protected AchievementRetrieve achievementRetrieve;

    @BeforeEach
    void setUp() {
        UserSession.getInstance().setUserName("Alex");
        UserSession.getInstance().setPassword("test");

        MyRestTemplate restTemplate = new MyRestTemplate("http://localhost:" + port + "/");

        this.achievementRetrieve = new AchievementRetrieve();
        this.achievementRetrieve.setRestTemplate(restTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void achGetAll() {
        List<Achievement> achievements  = this.achievementRetrieve.achGetAll();
        assertNotNull(achievements);

        assertNotNull(achievements);
        assertNotNull(achievements.get(0).getTitle());
    }

    @Test
    void achGet() {
        Achievement achievement = this.achievementRetrieve.achGet(1L);
        assertNotNull(achievement);
        assertEquals("GoGreen",  achievement.getTitle());
    }

    @Test
    void achGetUnlocked() {
        ArrayList<Achievement> achievements =  this.achievementRetrieve.achGetUnlocked(1L);
        assertNotNull(achievements);
        assertTrue(achievements.size() > 0);
    }

    @Test
    void addUserAchGood() {
        Achievement achievement = this.achievementRetrieve.achGet(3L);
        assertNotNull(achievement);

        ArrayList<Achievement> achievements =  this.achievementRetrieve.achGetUnlocked(1L);
        assertNotNull(achievements);
        assertTrue(achievements.size() > 0);

        int sizeBeforeUpdate = achievements.size();


        this.achievementRetrieve.addUserAch(1L, achievement.getID());

        achievements =  this.achievementRetrieve.achGetUnlocked(1L);
        assertNotNull(achievements);

        assertEquals(sizeBeforeUpdate +1, achievements.size());
    }
    @Test
    void addUserAchBadAchievement() {
        Achievement achievement = this.achievementRetrieve.achGet(-3L);
        assertNull(achievement);

        ArrayList<Achievement> achievements =  this.achievementRetrieve.achGetUnlocked(1L);
        assertNotNull(achievements);
        assertTrue(achievements.size() > 0);

        int sizeBeforeUpdate = achievements.size();


        this.achievementRetrieve.addUserAch(-1L, -3L);

        achievements =  this.achievementRetrieve.achGetUnlocked(1L);
        assertNotNull(achievements);

        assertEquals(sizeBeforeUpdate, achievements.size());
    }
    @Test
    void addUserAchBadUser() {
        Achievement achievement = this.achievementRetrieve.achGet(3L);
        assertNotNull(achievement);
        this.achievementRetrieve.addUserAch(-1L, achievement.getID());
        ArrayList<Achievement> achievements=null;
        achievements = this.achievementRetrieve.achGetUnlocked(-1L);
        assertNull(achievements);
    }
    @Test
    void addAch() {
        List<Achievement> achievements  = this.achievementRetrieve.achGetAll();
        assertNotNull(achievements);

        int amountBeforeUpdate = achievements.size();

        Achievement newAchievement = new Achievement("genericTitle", "genericDescription", "path");
        this.achievementRetrieve.addAch(newAchievement);

        achievements  = this.achievementRetrieve.achGetAll();
        assertNotNull(achievements);
        assertEquals(amountBeforeUpdate +1, achievements.size());

    }
}