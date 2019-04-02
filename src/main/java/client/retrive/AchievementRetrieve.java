package client.retrive;

import client.Service.MyRestTemplate;
import client.Service.UrlEndPoints;
import client.model.Achievement;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * The type Achievement controller.
 */
public class AchievementRetrieve {


    /**
     * Ach get all array list.
     *
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public static ArrayList<Achievement> achGetAll() {
        //GET ALL ACHIEVEMENTS
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Achievement[]> entity = new HttpEntity<>(headers);

        MyRestTemplate restTemplate = new MyRestTemplate();
        ResponseEntity<Achievement[]> response = restTemplate.exchange(UrlEndPoints.Achievements.URL_ACHGETALL,
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
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Achievement> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {achID};

        MyRestTemplate restTemplate = new MyRestTemplate();
        ResponseEntity<Achievement> response = restTemplate.exchange(UrlEndPoints.Achievements.URL_ACHGET,
            HttpMethod.POST, entity, Achievement.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            Achievement[] ach = new Achievement[1];
            ach[0] = response.getBody();

            if (response.getBody() != null) {

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
        MyRestTemplate restTemplate = new MyRestTemplate();

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Achievement[]> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {usrID};

        ResponseEntity<Achievement[]> response = restTemplate.exchange(UrlEndPoints.Achievements.URL_ACHUNLOCKED,
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

    public static void addUserAch(long usrID, long achID) {

        MyRestTemplate restTemplate = new MyRestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        Object[] uriValues = new Object[] {usrID, achID};
        ResponseEntity<Achievement> response;

        try {

            HttpEntity<Achievement> entity = new HttpEntity<>(headers);
            response = restTemplate.exchange(UrlEndPoints.Achievements.URL_ACHUNLOCKED,
                HttpMethod.POST, entity, Achievement.class, uriValues);

        } catch (Exception e) {

            System.out.println("(Client Side) Either the achievement doesnt exist, or the user.");
            System.out.println(e.toString());
            return;
        }

        Achievement responseValue = response.getBody();

        System.out.println("(Client Side) The achievement with id " + responseValue.getID()
            + "has been unlocked for user " + usrID);
    }

    public static void addAch(Achievement ach) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        try {

            HttpEntity<Achievement> requestBody = new HttpEntity<>(ach, headers);
            Achievement newAch = restTemplate.postForObject(UrlEndPoints.Achievements.URL_NEWACH, requestBody, Achievement.class);

            if (newAch != null && newAch.getID() != 0) {

                System.out.println("(Client Side) The achievement " + ach.getTitle() + " has been created.");
            }

        } catch (Exception e) {

            System.out.println("(Client Side) Creating the achievement " + ach.getTitle() + " failed.");
            System.out.println(e.toString());
        }
    }
}
