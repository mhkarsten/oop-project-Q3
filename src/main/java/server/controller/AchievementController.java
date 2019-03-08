package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import server.model.Achievement;
import server.model.User;
import server.repository.AchievementRepository;
import server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AchievementController {
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/achievements",
            method = {RequestMethod.POST,RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Achievement> getAchievements() {
        return achievementRepository.findAll();
    }

    /**
     * Gets a specific achievement by achievementID.
     * @param achID The achID to look for
     * @return The achievement if it exists
     */
    @RequestMapping(value = "/achievements/{achID}", method = {RequestMethod.POST,RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Achievement getAchievement(@PathVariable("achID") long achID) {
        Optional<Achievement> ach = achievementRepository.findById(achID);
        if (ach.isPresent()) {
            return ach.get();
        } else {
            return null;
        }
    }

    /**
     * A mapping to only get the achievements unlocked by a certain user.
     * @param userID the user id of the user
     * @return the unlocked achievements if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/achievements",method = {RequestMethod.POST,RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<Achievement> getUserAchievements(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) {
            return user.get().getAchievements();
        } else {
            return null;
        }
    }
}
