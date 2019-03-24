package client;

import client.repository.AchievementRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

//Currently this client side code is build to work with XML code whereas the server
//side code can use both XML and JSON. Later this might be changed so everything uses
//JSON for the sake of simplicity;
public class ClientController {

    @Autowired
    private AchievementRepository achievementRepository;

    static final String URL_USERS = "http://localhost:8080/users";
    static final String URL_NEWUSER = "http://localhost:8080/newUser";
    static final String URL_CHOOSEUSER = "http://localhost:8080/user/{userID}";
    static final String URL_ARBUSER = "http://localhost:8080/user";


    static final String USER_NAME = "tom";
    static final String PASSWORD = "123";

    private RestTemplate restTemplate;

    /**
     * Authenticated get (READ).
     */
    public void authGet() {

        HttpHeaders headers = new HttpHeaders();

        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encAuth);

        headers.set("Authorization", authHeader);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_USERS, //
            HttpMethod.GET, entity, String.class);

        String result = response.getBody();

        System.out.println(result);
    }


    public ClientController() {
        this.restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("micheal", "micheal"));
    }

    /**Method to return an arraylist of all users.
     *
     * @return Return all users from the server
     */
    public ArrayList<User> getUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User[]> response = restTemplate.exchange(URL_USERS,
                HttpMethod.POST, entity, User[].class);

        HttpStatus statusCode = response.getStatusCode();
        System.out.println("(Client Side) The http status code is: " + statusCode);

        //If status == 200
        if (statusCode == HttpStatus.OK) {

            User[] list = response.getBody();

            ArrayList<User> userList = new ArrayList<>();

            if (list != null) {

                userList.addAll(Arrays.asList(list));

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
    public User[] getUser(String userID) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<User[]> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        Object[] uriValue = new Object[] {userID};

        ResponseEntity<User[]> response = restTemplate.exchange(URL_CHOOSEUSER,
            HttpMethod.POST, entity, User[].class, uriValue);

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

}
