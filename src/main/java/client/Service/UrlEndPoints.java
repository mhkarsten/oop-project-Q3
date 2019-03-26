package client.Service;

public class UrlEndPoints {

    public static String BASE_URL = "http://localhost:8090/";

    public static class Auth {
        public  static final String REGISTER = BASE_URL + "auth/register";
        public  static final String LOGIN = BASE_URL + "auth/login";
    }

    public static class User {
        public static final String ALL_USERS = BASE_URL + "users";

        public static final String UPDATE =  BASE_URL + "users/update";
        public static final String USER_BY_ID = BASE_URL + "users/{userID}";

    }

    public static class Achievements {
        public static final String URL_ACHGETALL = BASE_URL + "achievements";
        public static final String URL_ACHGET = BASE_URL + "achievements/{achID}";
        public static final String URL_ACHUNLOCKED = BASE_URL + "users/{userID}/achievements";
    }
}
