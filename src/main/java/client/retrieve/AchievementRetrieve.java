package client.retrieve;

import client.model.Achievement;
import client.service.MyRestTemplate;
import client.service.UrlEndPoints;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The type Achievement controller.
 */
public class AchievementRetrieve extends BaseRetrieve {


    /**
     * Ach get all array list.
     *
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<Achievement> achGetAll() {
        //GET ALL ACHIEVEMENTS
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Achievement[]> entity = new HttpEntity<>(headers);

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
     * @param achID the ach id
     * @return the achievement
     */
    @SuppressWarnings("Duplicates")
    public Achievement achGet(long achID) {
        //GET SPECIFIC ACHIEVEMENT
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Achievement> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {achID};

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
     * @param usrID the usr id
     * @return the array list
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<Achievement> achGetUnlocked(long usrID) {
        //GET ALL UNLOCKED ACHIEVEMENTS BY A USER
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

    /**
     * Add user ach.
     *
     * @param usrID the usr id
     * @param achID the ach id
     */
    public void addUserAch(long usrID, long achID) {
        //ADDS AN ACHIEVEMENT TO THE USERS ACCOUNT
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        Object[] uriValues = new Object[] {usrID, achID};
        ResponseEntity<Achievement> response;

        try {

            HttpEntity<Achievement> entity = new HttpEntity<>(headers);
            response = restTemplate.exchange(UrlEndPoints.Achievements.URL_UNLOCKACHFORUSER,
                HttpMethod.POST, entity, Achievement.class, uriValues);
        } catch (RestClientException e) {

            System.out.println("(Client Side) Either the achievement doesnt exist, or the user.");
            System.out.println(e.toString());
            return;
        }
    }

    /**
     * Add ach.
     *
     * @param ach the ach
     */
    public void addAch(Achievement ach) {
        //CREATES A NEW ACHIEVEMENT
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        try {

            HttpEntity<Achievement> requestBody = new HttpEntity<>(ach, headers);
            Achievement newAch = restTemplate.postForObject(UrlEndPoints.Achievements.URL_NEWACH, requestBody, Achievement.class);

            if (newAch != null && newAch.getID() != 0) {

                System.out.println("(Client Side) The achievement " + ach.getTitle() + " has been created.");
            }
        } catch (RestClientException e) {

            System.out.println("(Client Side) Creating the achievement " + ach.getTitle() + " failed.");
            System.out.println(e.toString());
        }
    }
}
