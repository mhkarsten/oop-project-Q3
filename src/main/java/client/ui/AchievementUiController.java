package client.ui;

import client.model.Achievement;
import client.model.User;
import client.retrieve.AchievementRetrieve;
import client.service.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AchievementUiController implements Initializable {

    @FXML
    Label achievementName;

    @FXML
    ListView unlockedAchievements;
    @FXML
    ListView lockedAchievements;

    @FXML
    TextArea achievementDescription;

    @FXML
    ImageView achievementImage;

    private User activeUser;
    private ArrayList<Achievement> userAchievements;
    private ArrayList<Achievement> lockedAchives;
    private AchievementRetrieve achievementRetrieve;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.achievementRetrieve = new AchievementRetrieve();
        activeUser = UserSession.getInstance().getCurrentUser();
        userAchievements = this.achievementRetrieve.achGetUnlocked(activeUser.getID());
        lockedAchives = this.achievementRetrieve.achGetAll();

        if (lockedAchives != null) {

            lockedAchives.removeAll(userAchievements);
        }

        unlockedAchievements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lockedAchievements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        fillListView(unlockedAchievements, userAchievements);
        fillListView(lockedAchievements, lockedAchives);

        setListViewDoubleClick(unlockedAchievements);
        setListViewDoubleClick(lockedAchievements);
    }

    private void setListViewDoubleClick(ListView lv) {

        lv.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {
                ObservableList<String> clickedAch;
                clickedAch = lv.getSelectionModel().getSelectedItems();

                displayAchievement(clickedAch.get(0));
            }
        });
    }

    /**
     * Method to fill in the listView.
     * @param lv the listView to fill
     * @param al an ArrayList with the achievement to fill the listView with
     */
    public void fillListView(ListView lv, ArrayList<Achievement> al) {

        ObservableList<String> followeeList = lv.getItems();

        al.forEach(ach -> {

            followeeList.add(ach.getTitle());
        });
    }

    /**
     * Method to display a single achievement based on the title.
     * @param title the title of the achievement to display
     */
    public void displayAchievement(String title) {

        Achievement seleceted = findAchievement(title);

        try {

            achievementImage.setImage(new Image(new FileInputStream(seleceted.getPath())));
        } catch (FileNotFoundException e) {

            System.out.println("The image was not found");
        }


        achievementDescription.setText(seleceted.getDescription());
        achievementName.setText(seleceted.getTitle());
    }

    /**
     * Method to find an achievement based on the title.
     * @param title the title of the achievement to be found
     * @return returns the achievement if it was found
     */
    public Achievement findAchievement(String title) {

        ArrayList<Achievement> allAchives = new ArrayList<>();
        allAchives.addAll(userAchievements);
        allAchives.addAll(lockedAchives);

        for (int i = 0; i < allAchives.size(); i++) {

            if (allAchives.get(i).getTitle().equals(title)) {

                return allAchives.get(i);
            }
        }

        System.out.println("(Client) This Achievement doesnt exist: " + title);
        return null;
    }
}
