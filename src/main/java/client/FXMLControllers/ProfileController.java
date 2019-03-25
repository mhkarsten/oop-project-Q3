package client.FXMLControllers;

import client.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static client.serverCommunication.UserController.getUser;

public class ProfileController implements Initializable {

    @FXML
    public Label userName;
    public Label userID;
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User[] currentUser = getUser(1L);

        userName.setText("User Name: " + currentUser[0].getName());
        userID.setText("User ID: " + currentUser[0].getID());
        userPoints.setText("User Points: " + currentUser[0].getPoints());
    }
}
