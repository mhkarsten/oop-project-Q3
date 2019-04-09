package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import server.model.User;
import server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Initial connect message.
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
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},
        value = "/users/{userID}",
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("userID") long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},
        value = "/users/byName/{userName}",
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUserByName(@PathVariable("userName") String userName) {
        return userRepository.findByName(userName).get();
    }

    /**
     * Deletes an existing user (DELETE).
     *
     * @param userID Parameter for the userID of the user that has to be deleted
     */
    @DeleteMapping(value = "/users/{userID}")
    @ResponseBody
    public void deleteUser(@PathVariable("userID") @Valid long userID) {
        User user = userRepository.findById(userID).get();
        userRepository.delete(user);
    }

    /**
     * A mapping to only get the users someone is following.
     *
     * @param userID the user id of the user
     * @return the followers if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/following", method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
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
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public Set<User> getUserFollowers(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get().getFollowers();
    }

    /**
     * A mapping for following a user.
     *
     * @param followerId the one who is going to follow the followee
     * @param followeeId the one which is going to be followed by the follower
     */
    @RequestMapping(value = "/users/{followerId}/follow/{followeeId}",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public void followUser(@PathVariable("followerId") long followerId, @PathVariable("followeeId") long followeeId) {
        User follower = userRepository.findById(followerId).get();
        User followee = userRepository.findById(followeeId).get();
        followee.addFollower(follower);
        followee = userRepository.save(followee);
        follower.followUser(followee);
        userRepository.save(follower);
    }
}
