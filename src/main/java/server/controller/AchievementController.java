package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.model.Achievement;
import server.model.User;
import server.repository.AchievementRepository;
import server.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
public class AchievementController {
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/achievements/new",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> addAchievement(@RequestBody Achievement ach) {
        ach.setID(0);
        Achievement newAch = achievementRepository.save(ach);

        System.out.println("(Server Side) Creating new Achievement with ID: " + newAch.getID());

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/users/{userID}")
            .buildAndExpand(newAch.getID()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Gets all of the achievements that can currently be unlocked by users.
     *
     * @return the list of achievements
     */
    @RequestMapping(value = "/achievements",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Achievement> getAchievements() {
        return achievementRepository.findAllByOrderByIdAsc();
    }

    /**
     * Gets a specific achievement by achievementID.
     *
     * @param achID The achID to look for
     * @return The achievement if it exists
     */
    @RequestMapping(value = "/achievements/{achID}",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Achievement getAchievement(@PathVariable("achID") long achID) {
        return achievementRepository.findById(achID).get();
    }

    /**
     * A mapping to only get the achievements unlocked by a certain user.
     *
     * @param userID the user id of the user
     * @return the unlocked achievements if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/achievements",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<Achievement> getUserAchievements(@PathVariable("userID") long userID) {
        return userRepository.findById(userID).get().getAchievements();
    }

    /**
     * A mapping for unlocking an achievement
     *
     * @param userID the user id of the user
     * @return the unlocked achievements if any and if the user exists
     */
    @RequestMapping(value = "/users/{userId}/achievements/unlock/{achId}",
        method = {RequestMethod.POST, RequestMethod.GET},
        produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public void unlockAchievement(@PathVariable("userId") long userID, @PathVariable("achId") long achId) {
        User user= userRepository.findById(userID).get();
        Achievement achievement = achievementRepository.findById(achId).get();
        achievement.addUser(user);
        achievement=achievementRepository.save(achievement);
        user.addAchievement(achievement);
        userRepository.save(user);
    }
}
