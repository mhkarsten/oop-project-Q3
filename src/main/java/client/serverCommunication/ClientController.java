package client.serverCommunication;

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

//Currently this client side code is build to work with XML code whereas the server
//side code can use both XML and JSON. Later this might be changed so everything uses
//JSON for the sake of simplicity;

public class ClientController {

    //static final String URL = "localhost:8090";

    private static String URL_USERS = "localhost:8090/users";
    private static String URL_NEWUSER = "localhost:8090/users/new";
    private static String URL_CHOOSEUSER = "localhost:8090/users/{userID}";
    private static String URL_ARBUSER = "localhost:8090/users";
    private static final String USER_NAME = "tom";
    private static final String PASSWORD = "123";

    public static HttpHeaders setAuthHeaders(HttpHeaders headers) {

        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encAuth);

        headers.set("Authorization", authHeader);

        return headers;
    }

    /**Method to return an ArrayList of all users.
     *
     * @return Return all users from the server
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<User> getUsers() {

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers);
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);

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

                for (User u : list) {

                    userList.add(u);
                }

                return userList;
            }
        }

        return null;
    }

    /**Method to return a specified user.
     *
     * @param userID The userid of the user you try to get
     * @return Return a user from the server
     */
    @SuppressWarnings("Duplicates")
    public static User[] getUser(long userID) {

        HttpHeaders headers = new HttpHeaders();
        setAuthHeaders(headers);
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        ResponseEntity<User> response = restTemplate.exchange(URL_CHOOSEUSER,
            HttpMethod.POST, entity, User.class, uriValue);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            User[] userArray = new User[1];

            userArray[0] = response.getBody();

            if (userArray != null) {

                System.out.println(userArray[0].toString());
                return userArray;
            } else {

                System.out.println("(Client Side) The spesified user was null or doesnt exist.");
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
        setAuthHeaders(headers);
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

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
        setAuthHeaders(headers);
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);

        String updatedUserUrl = URL_ARBUSER + "/update";
        System.out.println("This is the url; "+updatedUserUrl);
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

}
