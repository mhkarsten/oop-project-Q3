package client.Service;


import client.model.Achievement;
import client.model.User;

public class AchievementGenerator {

        private static User activeUser = UserSession.getInstace().getCurrentUser();

    public static Achievement progressivePointAchivement(User usr) {

        if (usr.equals(activeUser)) {

            double numAch = 0;
            double numPoints = 0;
            while (numPoints < usr.getPoints()) {

                numPoints = Math.pow(2, numAch);
                numAch++;
            }

            String pointAchTitle = "Point achievement: " + ;

            String description = "This achievement is for acquiring " + numPoints + " points!";

            Achievement newAch = new Achievement(
                pointAchTitle,
                description,

            );
        } else {

            System.out.println("(Client Side) This is not the active user.");
            return null;
        }
    }

    public static Achievement foodAchievement() {


    }

    public static Achievement transportAchievement() {


    }

    public static Achievement energyAchievement() {


    }
}
