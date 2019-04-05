package client.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        UserSession.getInstance().cleanUserSession();
    }

    @Test
    void equals(){
        UserSession userSession = UserSession.getInstance();
        assertTrue(userSession.equals(UserSession.getInstance()));
        assertFalse(UserSession.getInstance().equals(null));
    }

    @Test
    void getInstace() {
        // Because of singleton, session needs to be cleared after every call.
        assertEquals(UserSession.getInstance(), UserSession.getInstance());
        assertNotEquals(null, UserSession.getInstance());
    }

    @Test
    void setUserName() {
        String name = null;
        assertEquals(name, UserSession.getInstance().getUserName());

        name = "thom";
        assertNotEquals(name, UserSession.getInstance().getUserName());

        UserSession.getInstance().setUserName(name);
        assertEquals(name, UserSession.getInstance().getUserName());
    }

    @Test
    void getUserName() {
        String name = null;
        assertEquals(name, UserSession.getInstance().getUserName());


        name = "thom";
        assertNotEquals(name, UserSession.getInstance().getUserName());

        UserSession.getInstance().setUserName(name);
        assertEquals(name, UserSession.getInstance().getUserName());

    }

    @Test
    void getPassword() {
        assertEquals(null, UserSession.getInstance().getPassword());

        String password = "123";
        assertNotEquals(password, UserSession.getInstance().getPassword());

        UserSession.getInstance().setPassword(password);
        assertEquals(password, UserSession.getInstance().getPassword());
    }

    @Test
    void setPassword() {
        assertEquals(null, UserSession.getInstance().getPassword());

        String password = "123";
        assertNotEquals(password, UserSession.getInstance().getPassword());

        UserSession.getInstance().setPassword(password);
        assertEquals(password, UserSession.getInstance().getPassword());
    }

    @Test
    void cleanUserSession() {
        // Check if the properties are empty
        assertEquals(null, UserSession.getInstance().getUserName());
        assertEquals(null, UserSession.getInstance().getPassword());

        // Fill the properties
        String name = "thom";
        String password = "123";
        UserSession.getInstance().setUserName(name);
        UserSession.getInstance().setPassword(password);

        // Now check if the properties are filled with the correct values
        assertEquals(name, UserSession.getInstance().getUserName());
        assertEquals(password, UserSession.getInstance().getPassword());
        // Check not null anymore
        assertNotEquals(null, UserSession.getInstance().getUserName());
        assertNotEquals(null, UserSession.getInstance().getPassword());

        // Clear the user session (Should set the properties to null again)
        UserSession.getInstance().cleanUserSession();

        // confirm that the properties are not equal to the set values anymore
        assertNotEquals(name, UserSession.getInstance().getUserName());
        assertNotEquals(password, UserSession.getInstance().getPassword());

        // confirm that the properties are set to null again
        assertEquals(null, UserSession.getInstance().getUserName());
        assertEquals(null, UserSession.getInstance().getPassword());

    }

}