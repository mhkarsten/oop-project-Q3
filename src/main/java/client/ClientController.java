package client;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

//Currently this client side code is build to work with XML code whereas the server side code can use both XML and JSON.
//Later this might be changed so everything uses JSON for the sake of simplicity;

public class ClientController {

    static final String URL_USERS = "http://localhost:8080/users";
    static final String URL_NEWUSER = "http://localhost:8080/newUser";
    static final String URL_DELETE = "http://localhost:8080/user/{userID}";

    //Get all users from the server (READ)
    public ArrayList<User> getUsers() {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_XML}));

        headers.setContentType(MediaType.APPLICATION_XML);
        headers.set("my_key", "my_value");

        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User[]> response = restTemplate.exchange(URL_USERS,
                HttpMethod.GET, entity, User[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if(statusCode == HttpStatus.OK) {

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

    //Get a user from the server (READ)
    public User getUser(String userID) {

        User user = new User();
        return user;
    }

    //Post a new user (CREATE)
    public void postUser(String userName, String userID) {

        User newUser = new User(userID, userName, 0);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);

        User u = restTemplate.postForObject(URL_NEWUSER, requestBody, User.class);

        if (u != null && u.getUserID() != null) {

            System.out.println("(Client Side) New user created" + u.getUserID());
        } else {

            System.out.println("(Client Side) Something went wrong.");
        }
    }

    //Update user information (UPDATE)
    public void updateUser(String userID) {


    }

    //Delete an existing user (DELETE)
    public void deleteUser(String userID) {

        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        restTemplate.delete(URL_DELETE, uriValue);

        User u = restTemplate.getForObject(URL_DELETE, User.class);

        if (u != null) {


        }
    }

}
