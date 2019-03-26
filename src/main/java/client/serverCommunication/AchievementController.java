package client.serverCommunication;

import client.Service.MyRestTemplate;
import client.Service.UrlEndPoints;
import client.model.Achievement;
import client.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Achievement controller.
 */
public class AchievementController {


    /**
     * Ach get all array list.
     *
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public  ArrayList<Achievement> achGetAll() {
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
    public  Achievement achGet(long achID) {
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
    public  ArrayList<Achievement> achGetUnlocked(long usrID) {
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

    public  void deleteUserAch(long usrID, long achID) {
        //REMOVE USER ACHIEVEMENT
    }

    public  void addUserAch(long usrID, long achID) {
        //ADD USER ACHIEVEMENT

    }
}
