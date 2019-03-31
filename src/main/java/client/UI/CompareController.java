package client.UI;

import client.model.User;
import javafx.collections.ObservableList;
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
import static client.retrive.UserRetrieve.updateUserFollowing;

/**
 * The type Compare controller.
 */
public class CompareController implements Initializable {

    /**
     * The Follow btn.
     */
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
    Set<User> userFollows;
    ArrayList<User> allUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userFollowing.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        userName.setText(activeUser.getName());
        userPoints.setText(Integer.toString(activeUser.getPoints()));

        User[] followingList = userFollows.toArray(new User[0]);

        for (int i = 0; i < userFollows.size(); i++) {

            userFollowing.getItems().add(followingList[i].getName());
        }

        allUsers = getUsers();
        userFollows = getUserFollow(false, activeUser.getID());
    }

    /**
     * Find user follow.
     */
    public void findUserFollow() {

        String nameToFind = userInput.getText();
        User foundUser = findUserByName(nameToFind);
        if ( foundUser != null) {

            userFollows.add(foundUser);
            updateUserFollowing(userFollows, activeUser.getID());

            findStatus.setText("You are now following" + foundUser.getName());
        } else {

            findStatus.setText("This user does not exist");
        }
    }

    /**
     * Sets compare.
     */
    public void setCompare() {

        ObservableList<String> usersFollowing;
        usersFollowing = userFollowing.getSelectionModel().getSelectedItems();

        User selectedUser = findUserByName(usersFollowing.get(0));

        compareName.setText(selectedUser.getName());
        comparePoints.setText(Integer.toString(selectedUser.getPoints()));
    }

    /**
     * Find user by name user.
     *
     * @param nameToFind the name to find
     * @return the user
     */
    public User findUserByName(String nameToFind) {
        Iterator findUser = allUsers.iterator();

        while(findUser.hasNext()) {

            User cUser = (User) findUser.next();

            if (cUser.getName().equals(nameToFind)) {

                return cUser;
            }
        }

        System.out.println("(Client Side) The user was not found.");
        return null;
    }
}
