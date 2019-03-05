package client.achievement;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;

public class AchievementMap {
    private static final Map<String, Achievement> AchMap = new HashMap<>();

    // TODO: Make that path dynamic instead of fixed to Dan Dan's Onedrive
    private static void initAchievements() {
        addAchievement(new Achievement("A01", "Start GoGreen", "You started to use GoGreen",
                "C:\\Users\\danda\\OneDrive\\Documenten\\template\\photos\\Ribbon_Award.png"));
        addAchievement(new Achievement("A02", "Green Fingers", "You planted a new plant", ""));
        addAchievement(new Achievement("A03", "Crazy good", "Achieve 666 points", ""));
    }

    public static Achievement getAchievement(String ach) {
        return AchMap.get(ach);
    }

    public static void addAchievement(Achievement ach) {
        AchMap.put(ach.getAchID(), ach);
    }

    /** Method to update an achievement based on the achievement ID.
     *
     * @param ach The update achievement
     */
    public static void updateAchievement(@NotNull Achievement ach) {
        if (AchMap.containsKey(ach.getAchID())) {
            AchMap.put(ach.getAchID(), ach);
        }
    }


}
