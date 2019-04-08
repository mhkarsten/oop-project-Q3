package client.service;

/**
 * Manages all api endpoints in a single class.
 */
public class UrlEndPoints {

    public static String BASE_URL = "http://localhost:8080/";

    /**
     * All authentication related api endpoints.
     */
    public static class Auth {
        public  static final String REGISTER =  "auth/register";
        public  static final String LOGIN =  "auth/login";
    }

    /**
     * All User related api endpoints.
     */
    public static class User {
        public static final String ALL_USERS =  "users";
        public static final String UPDATE =   "users/update";
        public static final String USER_BY_ID =  "users/{userID}";
        public static final String USER_FOLLOWERS =  "/users/{userID}/followers";
        public static final String USER_FOLLOWING =  "/users/{userID}/following";
        public static final String UPDATE_FOLLOW =  "/users/{followerId}/follow/{followeeId}";
        public static final String USER_BY_NAME = "/users/byName/{userName}";
    }

    /**
     * All Achievement related api endpoints.
     */
    public static class Achievements {
        public static final String URL_ACHGETALL =  "achievements";
        public static final String URL_ACHGET =  "achievements/{achID}";
        public static final String URL_ACHUNLOCKED =  "users/{userID}/achievements";
        public static final String URL_NEWACH =  "achievements/new";
        public static final String URL_UNLOCKACHFORUSER = "/users/{userId}/achievements/unlock/{achId}";
    }

    public static class Food {
        public static final String URL_MEAL = "/meal/{mealName}";
        public static final String URL_MEALCATEGORY = "/meals/{categoryName}";
        public static final String URL_RANDOMMEAL = "/randomMeal";
        public static final String URL_MEATMEALs = "/meals/meat";
    }

    public static class Emission {
        public static final String URL_VEHICLE =  "/vehicleEmission";
        public static final String URL_DIET =   "/dietEmission";
        public static final String URL_FLIGHT =  "/flightEmission";
        public static final String URL_ENERGY =  "/energyEmission";
        public static final String URL_TRAIN =  "/trainEmission";
    }

    public static class Feat {
        public static final String URL_USERFEATS = "/users/{userID}/feats";
    }
}
