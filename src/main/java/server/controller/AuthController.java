package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.model.User;
import server.repository.UserRepository;
import server.security.MyUserDetailsService;

import java.net.URI;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    /**
     * Initial connect message.
     *
     * @return Message stating you are connected
     */
    @RequestMapping("/auth/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }


    /**
     * Adds a new user (CREATE).
     *
     * @param usr Parameter for the user to be added
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
