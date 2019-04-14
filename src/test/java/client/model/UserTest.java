package client.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.Feat;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    private long id1 = 225;
    private long id2 = 123;


    private String name1 = "max";
    private String name2 = "genericName";

    private String password = "123";
    private String passwordTest = "genericPassword";

    private int points = 12;
    private int pointsTest = 123;

    private User us1;
    private User us2;
    private User us3;

    @BeforeEach
    void setUp() {
        this.us1 = new User(id1,name1);
        this.us2 = new User(id2,name2);
        this.us3 = new User(name1,password);
    }

    @AfterEach
    void tearDown() {
        this.us1 = null;
    }

    @Test
    void constructors() {
        User emptyUser = new User();
        assertNotNull(emptyUser);

        User user = new User(id1, name1);
        assertNotNull(user);
        assertEquals(id1, user.getID());
        assertEquals(name1, user.getName());

        user = new User(name1, password);
        assertNotNull(user);
        assertEquals(name1, user.getName());
    }

    @Test
    void equalsTest1() {
        Assertions.assertEquals(us1, us1);
    }

    @Test
    void equalsTest2() {
        Assertions.assertEquals(us1, new User(id1, name1));
    }

    @Test
    void equalsTest3() {
        Assertions.assertNotEquals(us1, null);
    }

    @Test
    void equalsTest4() {
        Assertions.assertNotEquals(us1, us2);
    }

    @Test
    void equalsTest5() {
        Assertions.assertNotEquals(us1, new User(id1, name2));
    }

    @Test
    void equalsTest6() {
        Assertions.assertNotEquals(us1, new User(id2, name1));
    }
    @Test
    void equalsTest7() {
        Assertions.assertNotEquals(us1, password);
    }
    @Test
    void getID() {
        User user = new User(id1, name1);
        assertEquals(id1, user.getID());
    }

    @Test
    void setID() {
        User user = new User(id1, name1);
        assertEquals(id1, user.getID());

        user.setID(id2);
        assertEquals(id2, user.getID());
    }

    @Test
    void getName() {
        assertEquals(name1, us1.getName());
    }

    @Test
    void setName() {
        assertEquals(name1, us1.getName());

        us1.setName(name2);
        assertEquals(name2, us1.getName());
    }

    @Test
    void calculatePoints() {
        assertEquals(0, this.us1.getPoints());
        this.us1.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        this.us1.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        this.us1.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        assertEquals(0, this.us1.getPoints());

        this.us1.calculatePoints();
        assertEquals(300, this.us1.getPoints());

    }

    @Test
    void getAndSetPoints() {
        assertEquals(0, this.us1.getPoints());
        this.us1.setPoints(points);

        assertEquals(points, this.us1.getPoints());

        this.us1.setPoints(pointsTest);
        assertEquals(pointsTest, this.us1.getPoints());
    }

    @Test
    void addFeat() {
        assertNull(this.us1.getFeats());

        Feat newFeat = new Feat(100, 0, new server.model.User("test", "test"));
        this.us1.addFeat(newFeat);

        assertNotNull(this.us1.getFeats());
        assertEquals(100, this.us1.getFeats().iterator().next().getPoints());
        assertTrue(newFeat.equals(this.us1.getFeats().iterator().next()));
    }

    @Test
    void getFeats() {
        assertNull(this.us1.getFeats());
        this.us1.addFeat(new Feat(100, 0, new server.model.User("test", "test")));
        assertNotNull(this.us1.getFeats());
    }

    @Test
    void getAndSetAchievements() {
        Achievement achievement = new Achievement("title", "description", "path");

        assertNull(this.us1.getAchievements());

        Set<Achievement> achievementSet = new HashSet<>();
        achievementSet.add(achievement);
        this.us1.setAchievements(achievementSet);

        assertEquals(achievementSet, this.us1.getAchievements());

    }

    @Test
    void addAchievement() {
        Achievement achievement = new Achievement("title", "description", "path");
        assertNull(this.us1.getAchievements());

        this.us1.addAchievement(achievement);
        this.us1.addAchievement(achievement);

        assertNotNull(this.us1.getAchievements());
    }

    @Test
    void getPassword() {
        assertEquals(password, this.us3.getPassword());
    }

    @Test
    void setPassword() {
        assertEquals(password, this.us3.getPassword());

        this.us3.setPassword(passwordTest);
        assertEquals(passwordTest, this.us3.getPassword());
    }
}