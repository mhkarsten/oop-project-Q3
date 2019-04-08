package client.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {
    private long id = 12;
    private long idTest = 123;
    private String title = "title";
    private String titleTest = "titleTest";
    private String description = "description";
    private String descriptionTest = "descriptionTest";
    private String path = "path";
    private String pathTest = "pathTest";

    private Achievement achievement;

    Set<User> users;
    Set<User> usersTest;

    @BeforeEach
    void setUp() {
        this.achievement = new Achievement(id, title, description, path);

        this.users = new HashSet<>();
        this.users.add(new User("test","test"));

        this.usersTest = new HashSet<>();
        this.usersTest.add(new User("new", "new"));
    }

    @AfterEach
    void tearDown() {
        this.achievement = null;
        this.users = null;
        this.usersTest = null;
    }

    @Test
    void equals() {
        assertTrue(this.achievement.equals(this.achievement));

        assertFalse(this.achievement.equals(new Achievement(idTest, titleTest, descriptionTest, pathTest)));

        assertFalse(this.achievement.equals(null));
    }

    @Test
    void constructors() {
        Achievement emptyAchievement = new Achievement();
        assertNotNull(emptyAchievement);

        Achievement achievement = new Achievement(title, description, path);
        assertNotNull(achievement);
    }

    @Test
    void getID() {
        assertEquals(id, this.achievement.getID());
        assertNotEquals(idTest, this.achievement.getID());
    }

    @Test
    void setID() {
        assertEquals(id, this.achievement.getID());

        this.achievement.setID(idTest);
        assertEquals(idTest, this.achievement.getID());

    }

    @Test
    void getTitle() {
        assertEquals(title, this.achievement.getTitle());
        assertNotEquals(titleTest, this.achievement.getTitle());
    }

    @Test
    void setTitle() {
        assertEquals(title, this.achievement.getTitle());

        this.achievement.setTitle(titleTest);
        assertEquals(titleTest, this.achievement.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(description, this.achievement.getDescription());
        assertNotEquals(descriptionTest, this.achievement.getDescription());
    }

    @Test
    void setDescription() {
        assertEquals(description, this.achievement.getDescription());

        this.achievement.setDescription(descriptionTest);
        assertEquals(descriptionTest, this.achievement.getDescription());
    }

    @Test
    void getPath() {
        assertEquals(path, this.achievement.getPath());
        assertNotEquals(pathTest, this.achievement.getPath());
    }

    @Test
    void setPath() {
        assertEquals(path, this.achievement.getPath());

        this.achievement.setPath(pathTest);
        assertEquals(pathTest, this.achievement.getPath());
    }
}