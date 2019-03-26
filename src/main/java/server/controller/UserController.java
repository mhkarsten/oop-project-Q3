package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.model.User;
import server.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Initial connect message.
     *
     * @return Message stating you are connected
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/")
    public void connect() {
    }

    /**
     * Gets all users.
     *
     * @return List of all users
     */
    @RequestMapping(value = "/users",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody

    public List<User> getUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    /**
     * Gets a specific user by userID.
     *
     * @param userID The userID to look for
     * @return The user if it exists
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/users/{userID}",
        produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("userID") long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        return optionalUser.get();
    }


    /**
     * Updates user information (POST).
     *
     * @param usr Parameter for the user to be updated
     * @return returns the updated user
     */
    @PutMapping(value = "/users/update",
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody User usr) {
        if (userRepository.findById(usr.getID()).isPresent()) {
            System.out.println("Updating user " + usr.getID());
            userRepository.save(usr);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Adds a new user (CREATE).
     *
     * @param usr Parameter for the user to be added
     * @return Returns the path at which the new user is located
     */
    @PostMapping(value = "/users/new",
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User usr) {
        User savedUser = userRepository.save(usr);
        System.out.println("Creating new user with ID" + savedUser.getID());

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/users/{userID}")
            .buildAndExpand(savedUser.getID()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Deletes an existing user (DELETE).
     *
     * @param userID Parameter for the userID of the user that has to be deleted
     */
    @DeleteMapping(value = "/users/{userID}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("userID") @Valid long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * A mapping to only get the users someone is following.
     *
     * @param userID the user id of the user
     * @return the followers if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/following", method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<User> getUserFollowing(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get().getFollowing();
    }

    /**
     * A mapping to only get the followers of a certain user.
     *
     * @param userID the user id of the user
     * @return the followers if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/followers", method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<User> getUserFollowers(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get().getFollowers();
    }
}
