package server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import server.model.Achievement;
import server.model.User;
import server.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class AchievementController {
    @Autowired
    private AchievementRepository achievementRepository;

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
    @RequestMapping(value = "/achievements/{achID}", method={RequestMethod.POST,RequestMethod.GET},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Achievement getAchievement(@PathVariable("achID") long achID) {
        Optional<Achievement> ach=achievementRepository.findById(achID);
        if(ach.isPresent())
        {
            return ach.get();
        }
        else return null;
    }

}
