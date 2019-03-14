package client.FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import client.serverCommunication.*;

import static client.serverCommunication.ClientController.getUser;

public class ScoreController implements Initializable {

    @FXML
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User[] currentUser = getUser(1L);

        userPoints.setText("Points: " + currentUser[0].getPoints());
    }
}
