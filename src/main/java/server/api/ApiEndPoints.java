package server.api;

/**
 * Manages all api endpoints in a single class.
 */
public class ApiEndPoints {
    public static class Food {
        public static String BASE = "https://www.themealdb.com";

        private static final String RANDOMEAL = "/api/json/v1/1/random.php";
        private static final String SPECIFICMEAL = "/api/json/v1/1/search.php?s=";
        private static final String CATEGORYMEAL = "/api/json/v1/1/filter.php?c=";
        public static final String getRandomMeal() {
            return BASE+RANDOMEAL;
        }
        public static final String getSpecificMeal() {
            return BASE+SPECIFICMEAL;
        }
        public static final String getCategoryMeal() {
            return BASE+CATEGORYMEAL;
        }
    }
    public static class Emission {
        static final String BASE = "http://impact.brighterplanet.com";
        static final String KEY = "key=5a927d96eca397b6659a3c361ce32254";
        static final String CAR = BASE + "/automobile_trips.json?";
        static final String ENERGY = BASE + "/residences.json?";
        static final String DIET = BASE + "/diets.json?";
        static final String FLIGHT = BASE + "/flights.json?";
        static final String TRAIN = BASE + "/rail_trips.json?";
    }
}
