package client.UI;

import client.Service.UserSession;
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

    private User activeUser;
    private Set<User> userFollows;
    private ArrayList<User> allUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        activeUser = UserSession.getInstace().getCurrentUser();
        allUsers = getUsers();
        userFollows = getUserFollow(false, activeUser.getID());

        userFollowing.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        userName.setText(activeUser.getName());
        userPoints.setText(Integer.toString(activeUser.getPoints()));

        updateListView();
    }

    public void updateListView() {

        User[] followingList = userFollows.toArray(new User[0]);

        for (int i = 0; i < userFollows.size(); i++) {

            userFollowing.getItems().add(followingList[i].getName());
        }
    }

    /**
     * Find user follow.
     */
    public void findUserFollow() {

        String nameToFind = userInput.getText();
        User foundUser = findUserByName(nameToFind);
        if ( foundUser != null) {

            userFollows.add(foundUser);

            updateUserFollowing(foundUser.getID(), activeUser.getID());
            userFollows = getUserFollow(false, activeUser.getID());
            updateListView();

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
