package client.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CompareController implements Initializable {

    @FXML
    Button followBtn;

    @FXML
    Label userName;
    @FXML
    Label compareName;
    @FXML
    Label userPoints;
    @FXML
    Label comparePoints;
    @FXML
    Label findStatus;

    @FXML
    TextField userInput;

    @FXML
    ListView userFollowing;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserSession.getInstace().getUser.getId();

    }

    public void findUserFollow() {


    }






}
