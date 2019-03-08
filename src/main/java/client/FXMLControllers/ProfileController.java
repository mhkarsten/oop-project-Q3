package client.FXMLControllers;
import client.serverCommunication.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private User activeUser;

    @FXML
    public Label userName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }

    public void getActiveUser(int userID) {


    }


}
