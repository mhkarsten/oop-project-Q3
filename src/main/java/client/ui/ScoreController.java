package client.UI;

import client.model.User;
import client.retrieve.UserRetrieve;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    public Label userPoints;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserRetrieve userRetrieve = new UserRetrieve();

        User[] currentUser = userRetrieve.getUser(1L);

        userPoints.setText("Your Points: " + currentUser[0].getPoints());
    }
}
