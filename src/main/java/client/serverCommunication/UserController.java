package client.serverCommunication;

import client.Service.MyRestTemplate;
import client.Service.UrlEndPoints;
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

public class UserController {




    /**Method to return an ArrayList of all users.
     *
     * @return Return all users from the server
     */
    @SuppressWarnings("Duplicates")
    public static ArrayList<User> getUsers() {
        MyRestTemplate restTemplate = new MyRestTemplate();

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);

        String url =  UrlEndPoints.User.ALL_USERS;
        ResponseEntity<User[]> response = restTemplate.exchange(url,
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

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {userID};

        String url =  UrlEndPoints.User.USER_BY_ID;

        MyRestTemplate restTemplate = new MyRestTemplate();

        ResponseEntity<User> response = restTemplate.exchange(url,
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

    /**Method to update a users information.
     * Update user information (UPDATE)
     */
    public static void updateUser(long userID, String userName, int points) {
        MyRestTemplate restTemplate = new MyRestTemplate();
        User updatedUser = new User(userID, userName, points);

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);

        System.out.println("This is the url; "+ UrlEndPoints.User.UPDATE);
        restTemplate.put(UrlEndPoints.User.UPDATE, requestBody);
    }

    /**Method to delete an existing user (DELETE).
     *
     * @param userID UserID of the user to delete
     */
    public static void deleteUser(String userID) {
        MyRestTemplate restTemplate = new MyRestTemplate();

        Object[] uriValue = new Object[] {userID};

        String url =  UrlEndPoints.User.USER_BY_ID;
        restTemplate.delete(url, uriValue);

        User user = restTemplate.getForObject(UrlEndPoints.User.ALL_USERS, User.class);

        if (user != null) {
            System.out.println("(Client Side) User " + user.getName() + " has been deleted.");
        } else {

            System.out.println("(Client Side) The selected client cannot be found"
                    + " or does not exist.");
        }
    }
}
