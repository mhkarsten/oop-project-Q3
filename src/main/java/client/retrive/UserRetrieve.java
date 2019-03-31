package client.retrive;

import client.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Method to return an ArrayList of all users.
 * Currently this client side code is build to work with XML code whereas the server
 * side code can use both XML and JSON. Later this might be changed so everything uses
 * JSON for the sake of simplicity;
 *
 * @return Return all users from the server
 */
public class UserRetrieve {

    /**Method to return an ArrayList of all users.
     *
     * @return Return all users from the server
     */
    private static final String URL_FOLLOWERS = "http://localhost:8090/users/{userID}/followers";
    private static final String URL_FOLLOWING = "http://localhost:8090/users/{userID}/following";
    private static final String URL_USERS = "http://localhost:8090/users";
    private static final String URL_NEWUSER = "http://localhost:8090/users/new";
    private static final String URL_CHOOSEUSER = "http://localhost:8090/users/{userID}";
    private static final String URL_ARBUSER = "http://localhost:8090/users";
    private static final String USER_NAME = "tom";
    private static final String PASSWORD = "123";

    public static HttpHeaders setAuthHeaders(HttpHeaders headers, boolean list) {

        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encAuth);

        headers.set("Authorization", authHeader);

        if (list) {

            headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
            headers.setContentType(MediaType.APPLICATION_XML);
        } else {

            headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
            headers.setContentType(MediaType.APPLICATION_XML);
        }

        return headers;
    }

    /**Method to return an ArrayList of all users.
     *
     * @return Return all users from the server
     */
    @SuppressWarnings("Duplicates")
    public static ArrayList<User> getUsers() {

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, true);

        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User[]> response = restTemplate.exchange(URL_USERS,
                HttpMethod.POST, entity, User[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {

            User[] list = response.getBody();

            ArrayList<User> userList = new ArrayList<User>();

            if (list != null) {

                userList.addAll(Arrays.asList(list));

                return userList;
            }
        }

        return null;
    }

    /**Method to return a specified user.
     *
     * @param userID The userID of the user you try to get
     * @return Return a user from the server
     */
    @SuppressWarnings("Duplicates")
    public static User[] getUser(long userID) {

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, false);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValues = new Object[] {userID};

        ResponseEntity<User> response = restTemplate.exchange(URL_CHOOSEUSER,
            HttpMethod.POST, entity, User.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            User[] userArray = new User[1];
            userArray[0] = response.getBody();

            if (response.getBody() != null) {

                return userArray;
            } else {

                System.out.println("(Client Side) The specified user was null or doesnt exist.");
            }
        }

        return null;
    }

    /**Method to post a new user (CREATE).
     *
     * @param userName New Username
     * @param userID New UserID
     */
    public static void postUser(String userName, long userID) {

        User newUser = new User(userID, userName, 0);

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, false);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);

        User user = restTemplate.postForObject(URL_NEWUSER, requestBody, User.class);

        if (user != null) {

            System.out.println("(Client Side) New user created" + user.getID());
        } else {

            System.out.println("(Client Side) Something went wrong.");
        }
    }

    /**Method to update a users information.
     * Update user information (UPDATE)
     */
    public static void updateUser(long userID, String userName, int points) {

        User updatedUser = new User(userID, userName, points);

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, false);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);

        String updatedUserUrl = URL_ARBUSER + "/update";
        System.out.println("This is the url; "+ updatedUserUrl);
        restTemplate.put(updatedUserUrl, requestBody);
    }

    /**Method to delete an existing user (DELETE).
     *
     * @param userID UserID of the user to delete
     */
    public void deleteUser(String userID) {

        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        restTemplate.delete(URL_CHOOSEUSER, uriValue);

        User user = restTemplate.getForObject(URL_ARBUSER, User.class);

        if (user != null) {

            System.out.println("(Client Side) User " + user.getName() + " has been deleted.");
        } else {

            System.out.println("(Client Side) The selected client cannot be found"
                    + " or does not exist.");
        }
    }

    /**
     * Gets a users followers, or the users a given user is following
     *
     * @param selectFollow true for followers, false for following
     * @param userID       the user id
     * @return the user follow
     */
    public static Set<User> getUserFollow(boolean selectFollow, long userID) {

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers, false);

        String URL;

        if (selectFollow) {

            URL = URL_FOLLOWERS;
        } else {

            URL = URL_FOLLOWING;
        }

        HttpEntity<Set<User>> entity = new HttpEntity<Set<User>>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValues = new Object[] {userID};

        ResponseEntity<Set> response = restTemplate.exchange(URL,
            HttpMethod.POST, entity, Set.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {

            Set<User> followers = response.getBody();

            if (followers != null) {

                return followers;
            }
        }

        return null;
    }

    public void updateUserFollowing(Set<User> newFollowing) {


    }
}
