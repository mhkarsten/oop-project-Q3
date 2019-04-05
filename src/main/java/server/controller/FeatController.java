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
import java.util.List;
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
    @RequestMapping(value = "/users/{userID}/feats",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public Set<Feat> getUserFeats(@PathVariable("userID") long userID) {
        return userRepository.findById(userID).get().getFeats();
    }

    /**
     * Gets a specific feat by featId.
     *
     * @param featId The featId to look for
     * @return The feat if it exists
     */
    @RequestMapping(value = "/feats/{featId}",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Feat getAchievement(@PathVariable("featId") long featId) {
        return featRepository.findById(featId).get();
    }

    /**
     * Gets all of the feats of all users.
     *
     * @return the list of feats
     */
    @RequestMapping(value = "/feats",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Feat> getFeats() {
        return featRepository.findAllByOrderByIdAsc();
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
    public ResponseEntity<?> addFeat(@PathVariable("userId")@Valid long userID, @RequestBody Feat feat) {
        //Feat savedFeat = featRepository.save(feat);
        User user= userRepository.findById(userID).get();
        feat.setUser(user);
        feat=featRepository.save(feat);

        System.out.println("Creating new feat with ID "+feat.getId());

        user.addFeat(feat);
        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/feats/{featId}").buildAndExpand(feat.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    /**
     * Adds a new feat (CREATE).
     *
     * @param feat Parameter for the feat to be added
     * @return Returns the path at which the created feat is located
     */
    @PostMapping(value = "/users/{userId}/feats/new/{points}",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> addGenericFeat(@PathVariable("userId")@Valid long userID, @PathVariable("points")@Valid int points) {
        //Feat savedFeat = featRepository.save(feat);
        User user= userRepository.findById(userID).get();

        Feat feat = new Feat(points, 0, user);

        feat.setUser(user);
        feat=featRepository.save(feat);

        System.out.println("Creating new feat with ID "+feat.getId());

        user.addFeat(feat);
        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/feats/{featId}").buildAndExpand(feat.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
