package client.Service;

/**
 * Manages all API endpoints in a single class
 */
public class UrlEndPoints {

    public static String BASE_URL = "http://localhost:8080/";

    /**
     * All authentication related API endpoints
     */
    public static class Auth {
        public  static final String REGISTER = BASE_URL + "auth/register";
        public  static final String LOGIN = BASE_URL + "auth/login";
    }

    /**
     * All User related API endpoints
     */
    public static class User {
        public static final String ALL_USERS = BASE_URL + "users";

        public static final String UPDATE =  BASE_URL + "users/update";
        public static final String USER_BY_ID = BASE_URL + "users/{userID}";

    }

    /**
     * All Achievement related API endpoints
     */
    public static class Achievements {
        public static final String URL_ACHGETALL = BASE_URL + "achievements";
        public static final String URL_ACHGET = BASE_URL + "achievements/{achID}";
        public static final String URL_ACHUNLOCKED = BASE_URL + "users/{userID}/achievements";
    }
}
