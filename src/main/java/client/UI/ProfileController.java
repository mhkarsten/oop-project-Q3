package client.UI;

import client.Service.UserSession;
import client.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public Label userName;
    public Label userID;
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User currentUser = UserSession.getInstance().getCurrentUser();

        userName.setText("User Name: " + currentUser.getName());
        userID.setText("User ID: " + currentUser.getID());
        userPoints.setText("User Points: " + currentUser.getPoints());
    }
}
