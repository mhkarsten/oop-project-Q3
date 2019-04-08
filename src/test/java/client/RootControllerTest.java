package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static client.ui.RootController.stringToPoints;

public class RootControllerTest {

    @Test
    void stringToPointsTest() {
        String two =  "2";
        String Five = "5.0";
        Assertions.assertEquals(2, stringToPoints(two));
        Assertions.assertEquals(5, stringToPoints(Five));
    }
}
