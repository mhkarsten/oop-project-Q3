package client.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlEndPointsTest {
    private String TEST_BASE_URL = "http://localhost:8080/";
    private String INCORRECT_BASE_URL = "http://localljkjnhost:8080/";

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void baseUrl(){
        assertEquals(TEST_BASE_URL, UrlEndPoints.BASE_URL);
        assertNotEquals(INCORRECT_BASE_URL, UrlEndPoints.BASE_URL);
    }

    @Test
    void achievementUrls() {
        String achievements = "achievements";
        assertEquals( achievements, UrlEndPoints.Achievements.URL_ACHGETALL);
        assertNotEquals(INCORRECT_BASE_URL + achievements, UrlEndPoints.Achievements.URL_ACHGETALL);

        String achievementById = "achievements/{achID}";
        assertEquals( achievementById, UrlEndPoints.Achievements.URL_ACHGET);
        assertNotEquals(INCORRECT_BASE_URL + achievementById, UrlEndPoints.Achievements.URL_ACHGET);

        String achievementsForUsers = "users/{userID}/achievements";
        assertEquals( achievementsForUsers, UrlEndPoints.Achievements.URL_ACHUNLOCKED);
        assertNotEquals(INCORRECT_BASE_URL + achievementsForUsers, UrlEndPoints.Achievements.URL_ACHUNLOCKED);

    }

    @Test
    void userUrls() {
        String users = "users";
        assertEquals( users, UrlEndPoints.User.ALL_USERS);
        assertNotEquals(INCORRECT_BASE_URL + users, UrlEndPoints.User.ALL_USERS);

        String userById = "users/{userID}";
        assertEquals( userById, UrlEndPoints.User.USER_BY_ID);
        assertNotEquals(INCORRECT_BASE_URL + userById, UrlEndPoints.User.USER_BY_ID);

        String userUpdate = "users/update";
        assertEquals( userUpdate, UrlEndPoints.User.UPDATE);
        assertNotEquals(INCORRECT_BASE_URL + userUpdate, UrlEndPoints.User.UPDATE);
    }

    @Test
    void authUrls() {
        String register = "auth/register";
        assertEquals( register, UrlEndPoints.Auth.REGISTER);
        assertNotEquals(INCORRECT_BASE_URL + register, UrlEndPoints.Auth.REGISTER);

        String login = "auth/login";
        assertEquals( login, UrlEndPoints.Auth.LOGIN);
        assertNotEquals(INCORRECT_BASE_URL + login, UrlEndPoints.Auth.LOGIN);

    }
}