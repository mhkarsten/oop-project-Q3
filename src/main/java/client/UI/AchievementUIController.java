package client.UI;

import client.Service.UserSession;
import client.model.Achievement;
import client.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static client.retrive.AchievementRetrieve.achGetAll;
import static client.retrive.AchievementRetrieve.achGetUnlocked;

public class AchievementUIController implements Initializable {

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
    private ArrayList<Achievement> userAchives;
    private ArrayList<Achievement> lockedAchives;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        activeUser = UserSession.getInstace().getCurrentUser();
        userAchives = achGetUnlocked(activeUser.getID());
        lockedAchives = achGetAll();

        if (lockedAchives != null) {

            lockedAchives.removeAll(userAchives);
        }

        unlockedAchievements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lockedAchievements.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        fillListView(unlockedAchievements, userAchives);
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

    public void fillListView(ListView lv, ArrayList<Achievement> al) {

        ObservableList<String> followeeList = lv.getItems();

        al.forEach(ach -> {

            followeeList.add(ach.getTitle());
        });
    }

    public void displayAchievement(String title) {

        Achievement seleceted = findAchievement(title);

        achievementImage.setImage(new Image(seleceted.getPath()));
        achievementDescription.setText(seleceted.getDescription());
        achievementName.setText(seleceted.getTitle());
    }

    public Achievement findAchievement(String title) {

        ArrayList<Achievement> allAchives = new ArrayList<>();
        allAchives.addAll(userAchives);
        allAchives.addAll(lockedAchives);

        for (int i = 0; i < allAchives.size(); i++) {

            if (allAchives.get(i).getTitle().equals(title)) {

                return allAchives.get(i);
            }
        }

        System.out.println("(Client) This Achievement doesnt exist: " + title);;
         return null;
    }
}
