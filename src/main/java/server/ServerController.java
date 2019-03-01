package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServerController {

    @Autowired
    private UserDAO userDAO;

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

    /**Get all users (READ).
     *
     * @return A list of all users
     */
    @RequestMapping(value = "/users",
        method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<User> getUsers() {

        List<User> userList = UserDAO.getAllUsers();

        return userList;
    }


    //Get for a specific user (READ)
    @RequestMapping(value = "/user/{userID}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("userID") String userID) {

        return userDAO.getUser(userID);
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

        return UserDAO.addUser(usr);
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
    public User updateUser(@RequestBody User usr) {

        System.out.println("(Server Side) Updating a user.");

        return UserDAO.updateUser(usr);
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

        UserDAO.deleteUser(userID);
    }

    //Get for CO2


    //Get for achieves


    //Get for login
}
