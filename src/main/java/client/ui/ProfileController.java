package client.ui;

import client.model.Feat;
import client.model.User;
import client.retrieve.FeatRetrieve;
import client.service.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public Label userName;
    public Label userID;
    public Label userPoints;
    public ListView featListView;

    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentUser = UserSession.getInstance().getCurrentUser();

        userName.setText("User Name: " + currentUser.getName());
        userID.setText("User ID: " + currentUser.getID());
        userPoints.setText("User Points: " + currentUser.getPoints());

        //Setting up the list of users the active user is following
        FeatRetrieve featRetrive = new FeatRetrieve();

        featListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> listViewContents = featListView.getItems();
        ArrayList<Feat> tempArray = featRetrive.getUserFeats(currentUser.getID());

        tempArray.forEach(feat -> {

            listViewContents.add("You have earned " + feat.getPoints() + " points for an activity.");
        });
    }


}
