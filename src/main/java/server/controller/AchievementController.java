package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import server.model.Achievement;
import server.model.User;
import server.repository.AchievementRepository;
import server.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
public class AchievementController {
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<?> handleNoSuchElementException(
            NoSuchElementException ex) {
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
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
    @RequestMapping(value = "/achievements/{achID}", method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Achievement getAchievement(@PathVariable("achID") long achID) {
        Optional<Achievement> ach = achievementRepository.findById(achID);
        return ach.get();
    }

    /**
     * A mapping to only get the achievements unlocked by a certain user.
     *
     * @param userID the user id of the user
     * @return the unlocked achievements if any and if the user exists
     */
    @RequestMapping(value = "/users/{userID}/achievements", method = {RequestMethod.POST, RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Set<Achievement> getUserAchievements(@PathVariable("userID") long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get().getAchievements();
    }
}
