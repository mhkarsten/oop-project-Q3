package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.model.User;
import server.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Initial connect message.
     *
     * @return Message stating you are connected
     */
    @RequestMapping("/")
    public ResponseEntity<?> connect() {
        return ResponseEntity.ok().build();
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
    public ResponseEntity<User> getUser(@PathVariable("userID") long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
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
     * @return Returns the user that has been added
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
}
