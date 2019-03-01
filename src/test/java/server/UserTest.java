package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTest {
    String UsID5 = "U05";
    String UsID20 = "U20";
    String UsName5 = "Max";
    String UsName20 = "Jason";
    int points = 200;
    int points300 = 300;


    User us1 = new User(UsID5, UsName5, points);
    User us2 = new User(UsID20, UsName20, points300);


    @Test
    void getUserID() {
        Assertions.assertEquals(UsID5, us1.getUserID());
    }

    @Test
    void setUserID() {
        us2.setUserID("U21");
        Assertions.assertEquals("U21", us2.userID);
    }

    @Test
    void getUserName() {
        Assertions.assertEquals(UsName20, us2.getUserName());
    }

    @Test
    void setUserName() {
        us1.setUserName("Thom");
        Assertions.assertEquals("Thom", us1.userName);
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
