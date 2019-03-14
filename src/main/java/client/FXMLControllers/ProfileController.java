package client.FXMLControllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static server.controller.ServerController.getUser;

public class ProfileController implements Initializable {

    @FXML
    public Label userName;
    public Label userID;
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        server.model.User currentUser = getUser(1L);

        userName.setText("User Name: " + currentUser.getUserName());
        userID.setText("User ID: " + currentUser.getUserID());
        userPoints.setText("User Points: " + currentUser.getUserPoints());
    }
}
