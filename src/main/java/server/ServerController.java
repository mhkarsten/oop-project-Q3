package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ServerController {

    @Autowired
    private UserDAO userDAO;

    //Initial Connect
    @RequestMapping("/")
    @ResponseBody
    public String connect() {

        return "You are connected";
    }


    //Get for all users (READ)
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


    //Adds a new user (CREATE)
    @RequestMapping(value = "/newUser",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User usr) {

        System.out.println("Creaating new user."  + usr.getUserID());

        return UserDAO.addUser(usr);
    }


    //Updates user information (POST)
    @RequestMapping(value = "/userUpdate",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE))
    @ResponseBody
    public User updateUser(@RequestBody User usr) {



        System.out.println("(Server Side) Updating a user.");

        return UserDAO.updateUser(usr);
    }


    //Deletes an existing user (DELETE)
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
