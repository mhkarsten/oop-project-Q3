package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {

    String ID1 = "A01";
    String ID2 = "A02";
    String title1 = "GoGreener";
    String title2 = "Superman";
    String descrip1 = "Get 10 points";
    String descrip2 = "Use CO2 free transportation instead of the car";
    String path1 = "Nothing";
    String path2 = "A nice cape picture";

    Achievement ach1 = new Achievement(ID1, title1, descrip1, path1);
    Achievement ach2 = new Achievement(ID2, title2, descrip2, path2);

    @Test
    void getAchID() {
        Assertions.assertEquals(ID1, ach1.getAchID());
    }

    @Test
    void setAchID() {
        ach2.setAchID("A07");
        Assertions.assertEquals("A07", ach2.getAchID());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(title2, ach2.getTitle());
    }

    @Test
    void setTitle() {
        ach1.setTitle("Perfect score");
        Assertions.assertEquals("Perfect score", ach1.getTitle());
    }

    @Test
    void getDescription() {
        Assertions.assertEquals(descrip1, ach1.getDescription());
    }

    @Test
    void setDescription() {
        ach2.setDescription("Save the earth");
        Assertions.assertEquals("save the earth", ach2.getDescription());
    }

    @Test
    void getPath() {
        Assertions.assertEquals(path2, ach2.getPath());
    }

    @Test
    void setPath() {
        ach1.setPath("img:url.com");
        Assertions.assertEquals("img:url.com", ach1.getPath());
    }
}