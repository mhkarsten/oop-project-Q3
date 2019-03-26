package client.FXMLControllers;

import client.model.User;
import client.serverCommunication.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static client.serverCommunication.UserController.getUser;

public class ScoreController implements Initializable {

    @FXML
    public Label userPoints;

    private UserController userController = new UserController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User[] currentUser = getUser(1L);

        userPoints.setText("Your Points: " + currentUser[0].getPoints());
    }
}
