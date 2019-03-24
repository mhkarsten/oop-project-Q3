package client.serverCommunication;

import client.model.Achievement;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static client.serverCommunication.UserRetrieve.setAuthHeaders;

/**
 * The type Achievement controller.
 */
public class AchievementRetrieve {

    /**
     * The Url base.
     */
    private static final String URL_BASE = "http://localhost:8090";
    private static final String URL_ACHGETALL = URL_BASE + "/achievements";
    private static final String URL_ACHGET = URL_BASE + "/achievements/{achID}";
    private static final String URL_ACHUNLOCKED = URL_BASE + "/users/{userID}/achievements";

    /**
     * Ach get all array list.
     *
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public static ArrayList<Achievement> achGetAll() {
        //GET ALL ACHIEVEMENTS
        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<Achievement[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Achievement[]> response = restTemplate.exchange(URL_ACHGETALL,
            HttpMethod.POST, entity, Achievement[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {
            Achievement[] list = response.getBody();

            ArrayList<Achievement> achList = new ArrayList<>();

            if (list != null) {

                achList.addAll(Arrays.asList(list));

                return achList;
            }
        }

        System.out.println("(Client Side) Getting all Achievements failed.");
        return null;
    }

    /**
     * Ach get achievement.
     *
     * @return the achievement
     */
    @SuppressWarnings("Duplicates")
    public static Achievement achGet(long achID) {
        //GET SPECIFIC ACHIEVEMENT
        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, false);

        HttpEntity<Achievement> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValues = new Object[] {achID};

        ResponseEntity<Achievement> response = restTemplate.exchange(URL_ACHGET,
            HttpMethod.POST, entity, Achievement.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            Achievement[] ach = new Achievement[1];
            ach[0] = response.getBody();

            if(response.getBody() != null) {

                return ach[0];
            }
        }
        System.out.println("(Client Side) Getting the desired achievement failed.");
        return null;
    }

    /**
     * Ach get unlocked array list.
     *
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public static ArrayList<Achievement> achGetUnlocked(long usrID) {
        //GET ALL UNLOCKED ACHIEVEMENTS BY A USER
        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<Achievement[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValues = new Object[] {usrID};

        ResponseEntity<Achievement[]> response = restTemplate.exchange(URL_ACHUNLOCKED,
            HttpMethod.POST, entity, Achievement[].class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {
            Achievement[] list = response.getBody();

            ArrayList<Achievement> achList = new ArrayList<>();

            if (list != null) {

                achList.addAll(Arrays.asList(list));

                return achList;
            }
        }

        System.out.println("(Client Side) Getting all Achievements failed.");
        return null;
    }

    public static void deleteUserAch(long usrID, long achID) {
        //REMOVE USER ACHIEVEMENT

    }

    public static void addUserAch(long usrID, long achID) {
        //ADD USER ACHIEVEMENT

    }
}
