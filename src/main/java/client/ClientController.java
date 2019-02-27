package client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

//Currently this client side code is build to work with XML code whereas the server side code can use both XML and JSON.
//Later this might be changed so everything uses JSON for the sake of simplicity;

public class ClientController {

    static final String URL_USERS = "http://localhost:8080/users";
    static final String URL_NEWUSER = "http://localhost:8080/newUser";
    static final String URL_CHOOSEUSER = "http://localhost:8080/user/{userID}";
    static final String URL_ARBUSER = "http://localhost:8080/user";


    public static final String USER_NAME = "tom";
    public static final String PASSWORD = "123";

    //Authenticated get (READ)
    public void authGet() {

        HttpHeaders headers = new HttpHeaders();

        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuthentication = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuthentication);

        headers.set("Authorization", authHeader);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_USERS, //
            HttpMethod.GET, entity, String.class);

        String result = response.getBody();

        System.out.println(result);
    }

    /**.
     *
     * @return Return all users from the server
     */
    public ArrayList<User> getUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User[]> response = restTemplate.exchange(URL_USERS,
                HttpMethod.GET, entity, User[].class);

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

    /**.
     *
     * @param userID The userid of the user you try to get
     * @return Return a user from the server
     */
    public User[] getUser(String userID) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        ResponseEntity<User[]> response = restTemplate.exchange(URL_CHOOSEUSER,
            HttpMethod.GET, entity, User[].class, uriValue);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            User[] specificUser = response.getBody();

            if ( specificUser != null) {

                return specificUser;
            } else {

                System.out.println("(Client Side) The spesified user was null or doesnt exist.");
            }
        }

        return null;
    }

    //Post a new user (CREATE)
    /**.
     *
     * @param userName New Username
     * @param userID New UserID
     */
    public void postUser(String userName, String userID) {

        User newUser = new User(userID, userName, 0);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);

        User user = restTemplate.postForObject(URL_NEWUSER, requestBody, User.class);

        if (user != null && user.getUserID() != null) {

            System.out.println("(Client Side) New user created" + user.getUserID());
        } else {

            System.out.println("(Client Side) Something went wrong.");
        }
    }

    //Update user information (UPDATE)
    public void updateUser(String userID, String userName, int points) {

        User updatedUser = new User(userID, userName, points);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);


        restTemplate.put(URL_ARBUSER, requestBody, new Object[]{});

        String updatedUserURL = URL_ARBUSER + "/" + userID;

        User u = restTemplate.getForObject(updatedUserURL, User.class);

        if (u != null) {

            System.out.println("(Client Side) User after info update." + u.getUserName() + u.getUserID() + u.getUserPoints());
        } else {

            System.out.println("(Client Side) Something went wrong, the user doesnt exits");
        }
    }

    //Delete an existing user (DELETE)
    /**.
     *
     * @param userID UserID of the user to delete
     */
    public void deleteUser(String userID) {

        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        restTemplate.delete(URL_CHOOSEUSER, uriValue);

        User user = restTemplate.getForObject(URL_ARBUSER, User.class);

        if (user != null) {

            System.out.println("(Client Side) User " + user.getUserName() + " has been deleted.");
        } else {

            System.out.println("(Client Side) The selected client cannot be found or does not exist.");
        }
    }

}
