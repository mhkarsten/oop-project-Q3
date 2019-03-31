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
        UserSession.getInstace().cleanUserSession();
    }

    @Test
    void getInstace() {
        // Because of singleton, session needs to be cleared after every call.
        assertEquals(UserSession.getInstace(), UserSession.getInstace());
        assertNotEquals(null, UserSession.getInstace());
    }

    @Test
    void setUserName() {
        String name = null;
        assertEquals(name, UserSession.getInstace().getUserName());

        name = "thom";
        assertNotEquals(name, UserSession.getInstace().getUserName());

        UserSession.getInstace().setUserName(name);
        assertEquals(name, UserSession.getInstace().getUserName());
    }

    @Test
    void getUserName() {
        String name = null;
        assertEquals(name, UserSession.getInstace().getUserName());


        name = "thom";
        assertNotEquals(name, UserSession.getInstace().getUserName());

        UserSession.getInstace().setUserName(name);
        assertEquals(name, UserSession.getInstace().getUserName());

    }

    @Test
    void getPassword() {
        assertEquals(null, UserSession.getInstace().getPassword());

        String password = "123";
        assertNotEquals(password, UserSession.getInstace().getPassword());

        UserSession.getInstace().setPassword(password);
        assertEquals(password, UserSession.getInstace().getPassword());
    }

    @Test
    void setPassword() {
        assertEquals(null, UserSession.getInstace().getPassword());

        String password = "123";
        assertNotEquals(password, UserSession.getInstace().getPassword());

        UserSession.getInstace().setPassword(password);
        assertEquals(password, UserSession.getInstace().getPassword());
    }

    @Test
    void cleanUserSession() {
        // Check if the properties are empty
        assertEquals(null, UserSession.getInstace().getUserName());
        assertEquals(null, UserSession.getInstace().getPassword());

        // Fill the properties
        String name = "thom";
        String password = "123";
        UserSession.getInstace().setUserName(name);
        UserSession.getInstace().setPassword(password);

        // Now check if the properties are filled with the correct values
        assertEquals(name, UserSession.getInstace().getUserName());
        assertEquals(password, UserSession.getInstace().getPassword());
        // Check not null anymore
        assertNotEquals(null, UserSession.getInstace().getUserName());
        assertNotEquals(null, UserSession.getInstace().getPassword());

        // Clear the user session (Should set the properties to null again)
        UserSession.getInstace().cleanUserSession();

        // confirm that the properties are not equal to the set values anymore
        assertNotEquals(name, UserSession.getInstace().getUserName());
        assertNotEquals(password, UserSession.getInstace().getPassword());

        // confirm that the properties are set to null again
        assertEquals(null, UserSession.getInstace().getUserName());
        assertEquals(null, UserSession.getInstace().getPassword());

    }

}