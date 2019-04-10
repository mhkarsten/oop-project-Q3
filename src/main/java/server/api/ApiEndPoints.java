package server.api;

/**
 * Manages all api endpoints in a single class.
 */
public class ApiEndPoints {
    public static class Food {
        static final String BASE = "https://www.themealdb.com";
        static final String RANDOMEAL = BASE+"/api/json/v1/1/random.php";
        static final String SPECIFICMEAL = BASE+"/api/json/v1/1/search.php?s=";
        static final String CATEGORYMEAL = BASE+"/api/json/v1/1/filter.php?c=";
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
