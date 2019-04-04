package client.UI;

import client.Service.MyRestTemplate;
import client.Service.UserSession;
import client.model.User;
import client.retrive.UserRetrieve;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;


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
    @FXML
    ListView followeeListView;

    private User activeUser;
    private Set<User> userFollows;
    private ArrayList<User> allUsers;
    private Set<User> userFollowingCurrent;

    private UserRetrieve userRetrieve;

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userRetrieve = new UserRetrieve();

        activeUser = UserSession.getInstance().getCurrentUser();
        allUsers = userRetrieve.getUsers();
        userFollows = userRetrieve.getUserFollow(false, activeUser.getID());
        userFollowingCurrent = userRetrieve.getUserFollow(true, activeUser.getID());

        userName.setText(activeUser.getName());
        userPoints.setText(Integer.toString(activeUser.getPoints()));

        //Setting up the list of users the active user is following
        userFollowing.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> listViewContents = userFollowing.getItems();
        ArrayList<Object> tempArray = new ArrayList<>();
        tempArray.addAll(userFollows);

        for (int i = 0; i < tempArray.size(); i++) {

            LinkedHashMap tempMap = (LinkedHashMap) tempArray.get(i);

            String userName = (String) tempMap.get("name");

            listViewContents.add(userName);
        }

        //Setting up the followee list
        followeeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> followeeList = followeeListView.getItems();
        ArrayList<Object> tempFolloweeArray = new ArrayList<>();
        tempFolloweeArray.addAll(userFollowingCurrent);

        for (int i = 0; i < tempFolloweeArray.size(); i++) {

            LinkedHashMap tempMap = (LinkedHashMap) tempFolloweeArray.get(i);

            String userName = (String) tempMap.get("name");

            followeeList.add(userName);
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

            userRetrieve.updateUserFollowing(activeUser.getID(), foundUser.getID());
            userFollowing.getItems().add(foundUser.getName());

            findStatus.setText("You are now following " + foundUser.getName());
        } else {

            findStatus.setText("This user does not exist");
        }
    }

    /**
     * Sets compare.
     */
    @SuppressWarnings("Duplicates")
    public void setCompare() {

        ObservableList<String> usersFollowing;
        usersFollowing = userFollowing.getSelectionModel().getSelectedItems();

        User selectedUser = findUserByName(usersFollowing.get(0));

        compareName.setText(selectedUser.getName());
        comparePoints.setText(Integer.toString(selectedUser.getPoints()));
    }

    /**
     * Sets compare followee.
     */
    @SuppressWarnings("Duplicates")
    public void setCompareFollowee () {

        ObservableList<String> userFollowee;
        userFollowee = followeeListView.getSelectionModel().getSelectedItems();

        User selectedUser = findUserByName(userFollowee.get(0));

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
