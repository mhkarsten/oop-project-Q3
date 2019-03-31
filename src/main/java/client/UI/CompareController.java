package client.UI;

import client.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import static client.retrive.UserRetrieve.getUserFollow;
import static client.retrive.UserRetrieve.getUsers;

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

    //UserSession.getInstace().getUser();
    User activeUser = new User();
    Set<User> userFollows = getUserFollow(false, activeUser.getID());

    ArrayList<User> allUsers = getUsers();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userFollowing.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        userName.setText(activeUser.getName());
        userPoints.setText(Integer.toString(activeUser.getPoints()));
        userFollowing.getItems().addAll(userFollows);
    }

    public void findUserFollow() {
        String nameToFind = userInput.getText();

        Iterator findUser = allUsers.iterator();

        while(findUser.hasNext()) {

            User cUser = (User) findUser.next();

            if (cUser.getName().equals(nameToFind)) {


            }
        }
    }
}
