package client;

import client.model.Achievement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AchievementTest {

    long ID1 = 1L;
    long ID2 = 2L;
    String title1 = "GoGreener";
    String title2 = "Superman";
    String descrip1 = "Get 10 points";
    String descrip2 = "Use CO2 free transportation instead of the car";
    String path1 = "Nothing";
    String path2 = "A nice cape picture";

    Achievement ach1 = new Achievement(ID1, title1, descrip1, path1);
    Achievement ach2 = new Achievement(ID2, title2, descrip2, path2);

    @Test
    void equalsTest1() {
        Assertions.assertEquals(ach1, ach1);
    }

    @Test
    void equalsTest2() {
        Assertions.assertEquals(ach1, new Achievement(ID1, title1, descrip1, path1));
    }

    @Test
    void equalsTest3() {
        Assertions.assertNotEquals(ach1, new Achievement(ID1, "Mother Nature's disciple", descrip1, path1));
    }

    @Test
    void equalsTest4() {
        Assertions.assertNotEquals(ach1, null);
    }

    @Test
    void getAchID() {
        Assertions.assertEquals(ID1, ach1.getAchID());
    }

    @Test
    void setAchID() {
        ach2.setAchID(7L);
        Assertions.assertEquals(7L, ach2.getAchID());
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
        ach2.setDescription("save the earth");
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