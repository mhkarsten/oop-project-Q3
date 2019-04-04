package client.UI;

import client.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static client.retrieve.UserRetrieve.getUser;

public class ScoreController implements Initializable {

    @FXML
    public Label userPoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User[] currentUser = getUser(1L);

        userPoints.setText("Your Points: " + currentUser[0].getPoints());
    }
}
