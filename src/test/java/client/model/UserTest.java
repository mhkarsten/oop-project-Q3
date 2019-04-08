package client.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.Feat;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    private long id = 225;
    private long idTest = 123;


    private String name = "max";
    private String nameTest = "genericName";

    private String password = "123";
    private String passwordTest = "genericPassword";

    private int points = 12;
    private int pointsTest = 123;

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User(name, password);
    }

    @AfterEach
    void tearDown() {
        this.user = null;
    }

    @Test
    void constructors() {
        User emptyUser = new User();
        assertNotNull(emptyUser);

        User user = new User(id, name);
        assertNotNull(user);
        assertEquals(id, user.getID());
        assertEquals(name, user.getName());

        user = new User(name, password);
        assertNotNull(user);
        assertEquals(name, user.getName());
    }


    @Test
    void equals() {
        assertTrue(this.user.equals(this.user));
        User randomUser = new User("random", "random");

        assertFalse(this.user.equals(randomUser));
        assertFalse(this.user.equals(null));

    }

    @Test
    void getID() {
        User user = new User(id, name);
        assertEquals(id, user.getID());
    }

    @Test
    void setID() {
        User user = new User(id, name);
        assertEquals(id, user.getID());

        user.setID(idTest);
        assertEquals(idTest, user.getID());
    }

    @Test
    void getName() {
        assertEquals(name, user.getName());
    }

    @Test
    void setName() {
        assertEquals(name, user.getName());

        user.setName(nameTest);
        assertEquals(nameTest, user.getName());
    }

    @Test
    void calculatePoints() {
        assertEquals(0, this.user.getPoints());
        this.user.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        this.user.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        this.user.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        assertEquals(0, this.user.getPoints());

        this.user.calculatePoints();
        assertEquals(300, this.user.getPoints());

    }

    @Test
    void getAndSetPoints() {
        assertEquals(0, this.user.getPoints());
        this.user.setPoints(points);

        assertEquals(points, this.user.getPoints());

        this.user.setPoints(pointsTest);
        assertEquals(pointsTest, this.user.getPoints());
    }

    @Test
    void addFeat() {
        assertNull(this.user.getFeats());

        Feat newFeat = new Feat(100, 0, new server.model.User("test", "test"));
        this.user.addFeat(newFeat);

        assertNotNull(this.user.getFeats());
        assertEquals(100, this.user.getFeats().iterator().next().getPoints());
        assertTrue(newFeat.equals(this.user.getFeats().iterator().next()));
    }

    @Test
    void getFeats() {
        assertNull(this.user.getFeats());
        this.user.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        assertNotNull(this.user.getFeats());
    }

    @Test
    void getAndSetAchievements() {
        Achievement achievement = new Achievement("title", "description", "path");

        assertNull(this.user.getAchievements());

        Set<Achievement> achievementSet = new HashSet<>();
        achievementSet.add(achievement);
        this.user.setAchievements(achievementSet);

        assertEquals(achievementSet, this.user.getAchievements());

    }

    @Test
    void addAchievement() {
        Achievement achievement = new Achievement("title", "description", "path");
        assertNull(this.user.getAchievements());

        this.user.addAchievement(achievement);

        assertNotNull(this.user.getAchievements());
    }

    @Test
    void getPassword() {
        assertEquals(password, this.user.getPassword());
    }

    @Test
    void setPassword() {
        assertEquals(password, this.user.getPassword());

        this.user.setPassword(passwordTest);
        assertEquals(passwordTest, this.user.getPassword());
    }
}