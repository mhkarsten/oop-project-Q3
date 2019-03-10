package client.FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import server.model.User;

import java.net.URL;
import java.util.ResourceBundle;

import static server.controller.ServerController.getUser;


public class ScoreController implements Initializable {

    @FXML
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User currentUser = getUser("3");

        userPoints.setText("Points: " + currentUser.getUserPoints());
    }
}
