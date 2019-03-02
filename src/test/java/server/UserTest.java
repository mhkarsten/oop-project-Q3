package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.model.User;


public class UserTest {
    long UsID5 = 5;
    long UsID20 = 20;
    String UsName5 = "Max";
    String UsName20 = "Jason";
    int points = 200;
    int points300 = 300;


    User us1 = new User(UsID5, UsName5, points);
    User us2 = new User(UsID20, UsName20, points300);

    @Test
    void equalsTest1() {
        Assertions.assertEquals(us1,us1);
    }
    @Test
    void equalsTest2() {
        Assertions.assertEquals(us1, new User(UsID5, UsName5, points));
    }
    @Test
    void equalsTest3() {
        Assertions.assertNotEquals(us1, new User(UsID5, UsName5, 42));
    }
    @Test
    void getUserID() {
        Assertions.assertEquals(UsID5, us1.getUserID());
    }

    @Test
    void setUserID() {
        us2.setUserID(21);
        Assertions.assertEquals(21, us2.getUserID());
    }

    @Test
    void getUserName() {
        Assertions.assertEquals(UsName20, us2.getUserName());
    }

    @Test
    void setUserName() {
        us1.setUserName("Thom");
        Assertions.assertEquals("Thom", us1.getUserName());
    }

    @Test
    void getUserPoints() {
        Assertions.assertEquals(200, us1.getUserPoints());
    }

    @Test
    void setUserPoints() {
        us2.setUserPoints(21);
        Assertions.assertEquals(21, us2.getUserPoints());
    }
}
