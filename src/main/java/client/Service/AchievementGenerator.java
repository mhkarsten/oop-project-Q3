package client.Service;


import client.model.Achievement;
import client.model.User;

import java.util.ArrayList;
import java.util.Random;

import static client.retrive.AchievementRetrieve.*;

public class AchievementGenerator {

    private static User activeUser = UserSession.getInstace().getCurrentUser();

    public static String bulBadgepath = "src/main/images/bulbbadge.jpg";
    public static String batteryBadgePath = "src/main/images/batterybadge.jpg";
    public static String candleBadge = "src/main/images/candlebadge.jpg";
    public static String cactusBadge = "src/main/images/cactusbadge.jpg";
    public static String fishBadge = "src/main/images/fishbadge.jpg";
    public static String powerPlantBadge = "src/main/images/powerplantbadge.jpg";
    public static String treeHouseBadge = "src/main/images/treehousebadge.jpg";
    public static String truckBadge = "src/main/images/truckbadge.jpg";
    public static String plugBadge = "src/main/images/plugbadge.jpg";
    public static String worldBadge = "src/main/images/worldbadge.jpg";

    public static void giveUserAch(User usr) {
        Achievement newAch = progressivePointAchivement(usr);

        
        addUserAch(usr.getID(), newAch.getID());
    }

    public static Achievement progressivePointAchivement(User usr) {

        if (usr.equals(activeUser)) {

            double numAch = 0;
            double numPoints = 0;
            while (numPoints < usr.getPoints()) {

                numPoints = Math.pow(2, numAch);
                numAch++;
            }

            String pointAchTitle = "Point achievement: " + numAch;

            String description = "This achievement is for acquiring " + numPoints + " points!";

            Achievement newAch = new Achievement(
                pointAchTitle,
                description,
                getRandomBadge());

            ArrayList<Achievement> allAchives =  achGetAll();

            for (int i = 0; i < allAchives.size(); i++) {

                if (allAchives.get(i).getTitle().equals(newAch.getTitle())) {

                    return allAchives.get(i);
                }
            }

            addAch(newAch);
            return newAch;
        } else {

            System.out.println("(Client Side) This is not the active user.");
            return null;
        }
    }

    public static Achievement foodAchievement() {

        return null;
    }

    public static Achievement transportAchievement() {

        return null;
    }

    public static Achievement energyAchievement() {

        return null;
    }

    private static String getRandomBadge() {
        ArrayList<String> badgeList = new ArrayList<>();
        badgeList.add(batteryBadgePath);
        badgeList.add(worldBadge);
        badgeList.add(plugBadge);
        badgeList.add(powerPlantBadge);
        badgeList.add(bulBadgepath);
        badgeList.add(truckBadge);
        badgeList.add(treeHouseBadge);
        badgeList.add(cactusBadge);
        badgeList.add(candleBadge);
        badgeList.add(fishBadge);

        Random random = new Random();

        return badgeList.get(random.nextInt(badgeList.size()));
    }
}
