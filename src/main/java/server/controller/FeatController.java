package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.model.Feat;
import server.model.User;
import server.repository.FeatRepository;
import server.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@RestController
public class FeatController {
    @Autowired
    private FeatRepository featRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * A mapping to only get the feats of a certain user.
     *
     * @param userID the user id of the user
     * @return the feats if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/feats", method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<Feat> getUserFeats(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get().getFeats();
    }

    /**
     * Adds a new feat (CREATE).
     *
     * @param feat Parameter for the feat to be added
     * @return Returns the path at which the created feat is located
     */
    @PostMapping(value = "/users/{userId}/feats/new",
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> addFeat(@PathVariable("userId")@Valid long userID) {
        //Feat savedFeat = featRepository.save(feat);
        Optional<User> optUser = userRepository.findById(userID);
        User user=optUser.get();
        user.addFeat(new Feat(1,1,1,new Date()));
        userRepository.save(user);
        System.out.println("Creating new feat with ID");

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/feats/").build().toUri();
        return ResponseEntity.created(location).build();
    }
    /*
    @PostMapping(value = "/users/{userId}/feats/new",
        produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> addFeat(@PathVariable("userId")@Valid long userID, @RequestBody Feat feat) {
        //Feat savedFeat = featRepository.save(feat);
        Optional<User> optUser = userRepository.findById(userID);
        User user=optUser.get();
        user.addFeat(feat);
        userRepository.save(user);
        System.out.println("Creating new feat with ID");

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/feats/").build().toUri();
        return ResponseEntity.created(location).build();
    }*/
}
