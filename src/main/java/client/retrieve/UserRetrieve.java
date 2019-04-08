package client.retrieve;

import client.model.User;
import client.service.MyRestTemplate;
import client.service.UrlEndPoints;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**Method to return an ArrayList of all users.
 *Currently this client side code is build to work with XML code whereas the server
 *side code can use both XML and JSON. Later this might be changed so everything uses
 *JSON for the sake of simplicity;
 */
public class UserRetrieve extends BaseRetrieve {

    /**Method to return an ArrayList of all users.
     *
     * @return Return all users from the server
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<User> getUsers() {

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
    public User[] getUser(long userID) {

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {userID};

        ResponseEntity<User> response = restTemplate.exchange(UrlEndPoints.User.USER_BY_ID,
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

    /**
     * Method for getting a user by their name.
     * @param userName name of the user to find
     * @return
     */
    public User getUserByName(String userName) {
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        HttpEntity<User> entity = new HttpEntity<>(headers);

        Object[] uriValues = new Object[] {userName};

        ResponseEntity<User> response = restTemplate.exchange(UrlEndPoints.User.USER_BY_NAME,
            HttpMethod.POST, entity, User.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        if (statusCode == HttpStatus.OK) {

            return response.getBody();
        }

        return null;

    }

    /**Method to update a users information.
     * Update user information (UPDATE)
     * TODO: this method is not gonna work with the current security setup.
     * There should be a method updateUserPoints that takes the ID/username and points to be added.
     *
     */
    public void addGenericFeat(Long userId, int points) {

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        restTemplate.exchange(UrlEndPoints.User.ALL_USERS + "/" + userId + "/feats/new/" + points,
            HttpMethod.POST, entity, Object.class);
    }

    /**
     * Gets a users followers, or the users a given user is following.
     *
     * @param selectFollow true for followers, false for following
     * @param userID       the user id
     * @return the user follow
     */
    public Set<User> getUserFollow(boolean selectFollow, long userID) {

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        String url;

        if (selectFollow) {

            url = UrlEndPoints.User.USER_FOLLOWERS;
        } else {

            url = UrlEndPoints.User.USER_FOLLOWING;
        }

        HttpEntity<Set<User>> entity = new HttpEntity<Set<User>>(headers);


        Object[] uriValues = new Object[] {userID};

        ResponseEntity<Set> response = restTemplate.exchange(url,
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

    /**
     * Method to update the followers of a user.
     * @param followerId id of the user that is being followed
     * @param followeeId id of the user that follows the other user
     */
    public void updateUserFollowing(long followerId, long followeeId) {

        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        String url = UrlEndPoints.User.UPDATE_FOLLOW;

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);


        Object[] uriValues = new Object[] {followerId, followeeId};

        ResponseEntity<Object> response = restTemplate.exchange(url,
            HttpMethod.POST, entity, Object.class, uriValues);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);
    }
}
