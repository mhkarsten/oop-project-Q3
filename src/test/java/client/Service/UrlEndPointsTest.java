package client.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlEndPointsTest {
    private String TEST_BASE_URL = "http://localhost:8090/";
    private String INCORRECT_BASE_URL = "http://locallhost:8090/";

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
        assertEquals(TEST_BASE_URL + achievements, UrlEndPoints.Achievements.URL_ACHGETALL);
        assertNotEquals( achievements, UrlEndPoints.Achievements.URL_ACHGETALL);
        assertNotEquals(INCORRECT_BASE_URL + achievements, UrlEndPoints.Achievements.URL_ACHGETALL);

        String achievementById = "achievements/{achID}";
        assertEquals(TEST_BASE_URL + achievementById, UrlEndPoints.Achievements.URL_ACHGET);
        assertNotEquals( achievementById, UrlEndPoints.Achievements.URL_ACHGET);
        assertNotEquals(INCORRECT_BASE_URL + achievementById, UrlEndPoints.Achievements.URL_ACHGET);

        String achievementsForUsers = "users/{userID}/achievements";
        assertEquals(TEST_BASE_URL + achievementsForUsers, UrlEndPoints.Achievements.URL_ACHUNLOCKED);
        assertNotEquals( achievementsForUsers, UrlEndPoints.Achievements.URL_ACHUNLOCKED);
        assertNotEquals(INCORRECT_BASE_URL + achievementsForUsers, UrlEndPoints.Achievements.URL_ACHUNLOCKED);

    }

    @Test
    void userUrls() {
        String users = "users";
        assertEquals(TEST_BASE_URL + users, UrlEndPoints.User.ALL_USERS);
        assertNotEquals( users, UrlEndPoints.User.ALL_USERS);
        assertNotEquals(INCORRECT_BASE_URL + users, UrlEndPoints.User.ALL_USERS);

        String userById = "users/{userID}";
        assertEquals(TEST_BASE_URL + userById, UrlEndPoints.User.USER_BY_ID);
        assertNotEquals( userById, UrlEndPoints.User.USER_BY_ID);
        assertNotEquals(INCORRECT_BASE_URL + userById, UrlEndPoints.User.USER_BY_ID);

        String userUpdate = "users/update";
        assertEquals(TEST_BASE_URL + userUpdate, UrlEndPoints.User.UPDATE);
        assertNotEquals( userUpdate, UrlEndPoints.User.UPDATE);
        assertNotEquals(INCORRECT_BASE_URL + userUpdate, UrlEndPoints.User.UPDATE);
    }

    @Test
    void authUrls() {
        String register = "auth/register";
        assertEquals(TEST_BASE_URL + register, UrlEndPoints.Auth.REGISTER);
        assertNotEquals( register, UrlEndPoints.Auth.REGISTER);
        assertNotEquals(INCORRECT_BASE_URL + register, UrlEndPoints.Auth.REGISTER);

        String login = "auth/login";
        assertEquals(TEST_BASE_URL + login, UrlEndPoints.Auth.LOGIN);
        assertNotEquals( login, UrlEndPoints.User.USER_BY_ID);
        assertNotEquals(INCORRECT_BASE_URL + login, UrlEndPoints.Auth.LOGIN);

    }
}