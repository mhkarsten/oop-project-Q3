package client.service;

import client.model.Achievement;
import client.model.User;
import client.retrieve.AchievementRetrieve;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Achievement generator.
 */
public class AchievementGenerator {

    public static String bulBadgepath = "file:images/bulbbadge.jpg";
    public static String batteryBadgePath = "file:images/batterybadge.jpg";
    public static String candleBadge = "file:images/candlebadge.jpg";
    public static String cactusBadge = "file:images/cactusbadge.jpg";
    public static String fishBadge = "file:images/fishbadge.jpg";
    public static String powerPlantBadge = "file:images/powerplantbadge.jpg";
    public static String treeHouseBadge = "file:images/treehousebadge.jpg";
    public static String truckBadge = "file:images/truckbadge.jpg";
    public static String plugBadge = "file:images/plugbadge.jpg";
    public static String worldBadge = "file:images/worldbadge.jpg";

    public static AchievementRetrieve achievementRetrieve = new AchievementRetrieve();

    private static User activeUser = UserSession.getInstance().getCurrentUser();

    /**
     * Create ach notification popup.
     *
     * @param ach the ach
     * @return the popup
     */
    public static Popup createAchNotification(final Achievement ach) {

        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        Label label = new Label("Congratulations you have earned the achievement: /n" + ach.getTitle());
        label.setOnMouseReleased(e -> popup.hide());
        label.getStylesheets().add("popup.css");
        label.getStyleClass().add("popup");

        popup.getContent().add(label);
        return popup;
    }

    /**
     * Ach notification.
     *
     * @param ach   the ach
     * @param stage the stage
     */
    public static void achNotification(final Achievement ach, final Stage stage) {

        if (ach == null) {

            return;
        }

        final Popup popup = createAchNotification(ach);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
            popup.setY(stage.getY() + stage.getHeight() / 2 - popup.getHeight() / 2);
        });
    }

    /**
     * Give user ach.
     *
     * @param usr the usr
     */
    public static Achievement giveUserAch(User usr) {

        Achievement newAch = progressivePointAchievement(usr);
        ArrayList<Achievement> allAchieves = achievementRetrieve.achGetAll();
        ArrayList<Achievement> usersAch = achievementRetrieve.achGetUnlocked(usr.getID());

        for (Achievement ach : usersAch) {
            if (ach.getTitle().equals(newAch.getTitle())) {

                System.out.println("(Client Side) This user already has the achievement.");
                return null;
            }
        }

        for (int i = 0; i < allAchieves.size(); i++) {
            if (allAchieves.get(i).getTitle().equals(newAch.getTitle())) {

                achievementRetrieve.addUserAch(usr.getID(), newAch.getID());
                return newAch;
            }
        }

        System.out.println("(Client Side) There was an issue with getting the achievementID.");
        return null;
    }

    /**
     * Progressive point achievement achievement.
     *
     * @param usr the usr
     * @return the achievement
     */
    public static Achievement progressivePointAchievement(User usr) {

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

            ArrayList<Achievement> allAchives = achievementRetrieve.achGetAll();

            for (int i = 0; i < allAchives.size(); i++) {

                if (allAchives.get(i).getTitle().equals(newAch.getTitle())) {

                    return allAchives.get(i);
                }
            }

            achievementRetrieve.addAch(newAch);
            return newAch;
        } else {

            System.out.println("(Client Side) This is not the active user.");
            return null;
        }
    }

    /**
     * Food achievement achievement.
     *
     * @return the achievement
     */
    public static Achievement foodAchievement() {

        return null;
    }

    /**
     * Transport achievement achievement.
     *
     * @return the achievement
     */
    public static Achievement transportAchievement() {

        return null;
    }

    /**
     * Energy achievement achievement.
     *
     * @return the achievement
     */
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
