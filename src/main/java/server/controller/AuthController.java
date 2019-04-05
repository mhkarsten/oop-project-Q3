package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.User;
import server.repository.UserRepository;
import server.security.MyUserDetailsService;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * Authenticates the user by basic authentication against the security configuration.
     *
     * @return a simple 200 message is successfully connected.
     */
    @PostMapping(value = "/auth/login/{username}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> login(@PathVariable("username") String username) {

        Optional<User> user = userRepository.findByName(username);

        if (!user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("(Server Side) " + username + " has logged in.");
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }


    /**
     * Registers a new user.
     *  First does the required checking and encryption and then persists the new user.
     *
     * @param user Parameter for the user to be added
     * @return Returns the user that has been added
     */
    @PostMapping(value = "/auth/register",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = myUserDetailsService.registerNewUserAccount(user);

        if (savedUser == null ) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Creating new user with ID" + savedUser.getID());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
