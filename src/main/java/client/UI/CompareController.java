package client.UI;

import client.Service.UserSession;
import client.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

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

        ObservableList<String> listViewContents = userFollowing.getItems();

        ArrayList<Object> tempArray = new ArrayList<>();
        tempArray.addAll(userFollows);

        for (int i = 0; i < tempArray.size(); i++) {

            LinkedHashMap tempMap = (LinkedHashMap) tempArray.get(i);

            String userName = (String) tempMap.get("name");

            listViewContents.add(userName);
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

            updateUserFollowing(activeUser.getID(), foundUser.getID());
            userFollowing.getItems().add(foundUser.getName());

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
