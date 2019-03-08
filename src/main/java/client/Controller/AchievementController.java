package client.Controller;

import client.model.Achievement;
import client.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AchievementController {
    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement addAchievement(Achievement achievement) {
        System.out.println("Creating new achievement: " + achievement.getAchID());
        return achievementRepository.save(achievement);
    }

    public Optional<Achievement> findAchievement(Achievement achievement) {
        return achievementRepository.findById(achievement.getAchID());
    }

    public List<Achievement> getAllAchievement() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getAllOwnedAchiement(List<Long> achievementlist) {
        return achievementRepository.findAllById(achievementlist);
    }

}
