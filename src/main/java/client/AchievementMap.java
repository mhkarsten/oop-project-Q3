package client;

import client.Achievement;

import java.util.HashMap;
import java.util.Map;

public class AchievementMap {
    private static final Map<String, Achievement> AchievMap = new HashMap<String, Achievement>();

    private static void intAchievements() {
        Achievement ach1 = new Achievement("A01", "Start GoGreen", "You started to use GoGreen good job");
        Achievement ach2 = new Achievement("A02", "Green Fingers", "You planted a new plant #GoOxygen");
        Achievement ach3 = new Achievement("A03", "Crazy good", "Achieve 666 points");

        AchievMap.put(ach1.getAchID(), ach1);
        AchievMap.put(ach2.getAchID(), ach2);
        AchievMap.put(ach2.getAchID(), ach2);
        AchievMap.put(ach3.getAchID(), ach3);
    }

    private static Achievement getAchievement(String ach) {
        return AchievMap.get(ach);
    }

    public static void addAchievement(Achievement achiev) {
        AchievMap.put(achiev.getAchID(), achiev);
    }

    public static void updateAchievement(Achievement achiev) {
        if(AchievMap.containsKey(achiev.getAchID())) {
            AchievMap.put(achiev.getAchID(), achiev);
        }
    }


}
