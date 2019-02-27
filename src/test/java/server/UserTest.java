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
        Assertions.assertEquals(us1.getUserID(), UsID5);
    }

    @Test
    void setUserID() {
        us2.setUserID("U21");
        Assertions.assertEquals(us2.userID, "U21");
    }

    @Test
    void getUserName() {
        Assertions.assertEquals(us2.getUserName(), UsName20);
    }

    @Test
    void setUserName() {
    }

    @Test
    void getUserPoints() {
    }

    @Test
    void setUserPoints() {
    }
}
