package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.model.User;
import server.repository.UserDao;
import server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ServerController {

    @Autowired
    private static UserRepository userRepository;

    /**Initial connect message.
     *
     * @return Message stating you are connected
     */
    @RequestMapping("/")
    @ResponseBody
    public String connect() {

        String connectString = "You are connected";
        return connectString;
    }

    /**
     * Gets all users.
     * @return List of all users
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets a specific user by userID.
     * @param userID The userID to look for
     * @return The user if it exists
     */
    @RequestMapping(value = "/user/{userID}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public static User getUser(@PathVariable("userID") String userID) {
        long id;
        try {
            id = Long.parseLong(userID);
        } catch (NumberFormatException ex) {
            return null;
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    /**Adds a new user (CREATE).
     *
     * @param usr Parameter for the user to be added
     * @return Returns the user that has been added
     */
    @RequestMapping(value = "/newUser",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User usr) {

        System.out.println("Creaating new user."  + usr.getUserID());

        return UserDao.addUser(usr);
    }

    /**Updates user information (POST).
     *
     * @param usr Parameter for the user to be updated
     * @return  returns the updated user
     */
    @RequestMapping(value = "/userUpdate",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public static User updateUser(@RequestBody User usr) {

        System.out.println("(Server Side) Updating a user.");

        return UserDao.updateUser(usr);
    }


    //Deletes an existing user (DELETE)

    /**Deletes an existing user (DELETE).
     *
     * @param userID Parameter for the userID of the user that has to be deleted
     */
    @RequestMapping(value = "/User/{userID}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("userID") String userID) {

        System.out.println("(Server Side) Deleting user" + userID);

        UserDao.deleteUser(userID);
    }

    //Get for CO2


    //Get for achieves


    //Get for login
}
