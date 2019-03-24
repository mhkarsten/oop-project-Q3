package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.model.Achievement;
import server.model.User;


public class UserTest {
    long id1 = 1;
    long id2 = 2;
    String name1 = "Max";
    String name2 = "Jason";


    User us1 = new User(id1, name1);
    User us2 = new User(id2, name2);

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
        Assertions.assertNotEquals(us1, new Achievement());
    }
    @Test
    void equalsTest8() {
        us1.setPoints(100);
        Assertions.assertNotEquals(us1, new User(id1, name1));
    }
    @Test
    void getUserID() {
        Assertions.assertEquals(id1, us1.getID());
    }

    @Test
    void setUserID() {
        us2.setID(21);
        Assertions.assertEquals(21, us2.getID());
    }

    @Test
    void getUserName() {
        Assertions.assertEquals(name2, us2.getName());
    }

    @Test
    void setUserName() {
        us1.setName("Thom");
        Assertions.assertEquals("Thom", us1.getName());
    }

    @Test
    void getUserPoints() {
        Assertions.assertEquals(0, us1.getPoints());
    }
}
