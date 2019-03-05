package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import server.model.User;
import server.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class ServerController {

    @Autowired
    private UserRepository userRepository;
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
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User parseUserID(String userID)
    {
        long id = -1;
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
    /**
     * Gets a specific user by userID.
     * @param userID The userID to look for
     * @return The user if it exists
     */
    @PostMapping(value = "/user/{userID}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("userID") String userID) {
        return parseUserID(userID);
    }

    /**Adds a new user (CREATE).
     *
     * @param usr Parameter for the user to be added
     * @return Returns the user that has been added
     */
    @PostMapping(value = "/newUser",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User usr) {

        System.out.println("Creating new user: "  + usr.getID());
        return userRepository.save(usr);
    }

    /**Updates user information (POST).
     *
     * @param usr Parameter for the user to be updated
     * @return  returns the updated user
     */
    @PutMapping(value = "/userUpdate",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User updateUser(@RequestBody User usr) {
        if(userRepository.findById(usr.getID()).isPresent())
        {
            System.out.println("Updating a user.");
            return userRepository.save(usr);
        }
        else return null;
    }
    /**Deletes an existing user (DELETE).
     *
     * @param userID Parameter for the userID of the user that has to be deleted
     */
    @DeleteMapping(value = "/user/{userID}")
    @ResponseBody
    public String deleteUser(@PathVariable("userID") String userID) {
        System.out.println("Deleting user" + userID);
        User user=parseUserID(userID);
        if(user!=null)
        {
            userRepository.deleteById(user.getID());
            return "Deleted user "+userID;
        }
        return "Could not delete user "+userID;
    }
    //Get for CO2


    //Get for achieves


    //Get for login
}
