package client.Service;

public class UrlEndPoints {

    public static String BASE_URL = "http://localhost:8090/";


    public static class User {
        public static String ALL_USERS = BASE_URL + "users";
        public  static String REGISTER = BASE_URL + "register";

        public static String UPDATE =  BASE_URL + "users/update";
        public static String USER_BY_ID = BASE_URL + "users/{userID}";

    }

    public static class Achievements {
        public static final String URL_ACHGETALL = BASE_URL + "achievements";
        public static final String URL_ACHGET = BASE_URL + "achievements/{achID}";
        public static final String URL_ACHUNLOCKED = BASE_URL + "users/{userID}/achievements";
    }
}
