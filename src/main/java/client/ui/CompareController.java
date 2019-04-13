package client.ui;

import static client.ui.RootController.stringToDouble;

import client.model.User;
import client.retrieve.UserRetrieve;
import client.service.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * The type Compare controller.
 */
public class CompareController implements Initializable {

    public BarChart<String, Number> barChart;
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
    private Set<User> userFollowingCurrent;

    private UserRetrieve userRetrieve;

    @SuppressWarnings("Duplicates")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userRetrieve = new UserRetrieve();

        activeUser = UserSession.getInstance().getCurrentUser();
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

        loadGraph();
    }

    /**
     * Find user follow.
     */
    public void findUserFollow() {

        String nameToFind = userInput.getText();
        User foundUser = userRetrieve.getUserByName(nameToFind);

        if (foundUser != null) {

            ArrayList<Object> tempArray = new ArrayList<>(userFollows);

            for (int i = 0; i < tempArray.size(); i++) {

                LinkedHashMap tempMap = (LinkedHashMap) tempArray.get(i);

                String userName = (String) tempMap.get("name");

                if (userName.equals(foundUser.getName())) {

                    findStatus.setText("You are already following " + foundUser.getName());
                    return;
                }
            }

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

        User selectedUser = userRetrieve.getUserByName(usersFollowing.get(0));

        compareName.setText(selectedUser.getName());
        comparePoints.setText(Integer.toString(selectedUser.getPoints()));
    }

    /**
     * Sets compare followee.
     */
    @SuppressWarnings("Duplicates")
    public void setCompareFollowee() {

        ObservableList<String> userFollowee;
        userFollowee = followeeListView.getSelectionModel().getSelectedItems();

        User selectedUser = userRetrieve.getUserByName(userFollowee.get(0));

        compareName.setText(selectedUser.getName());
        comparePoints.setText(Integer.toString(selectedUser.getPoints()));
    }

    /**
     * Loads the graph using the feats of the user.
     */
    public void loadGraph() {


        XYChart.Series currentUserSeries = new XYChart.Series();
        currentUserSeries.getData().add(new XYChart.Data(activeUser.getName(), activeUser.getPoints()));
        currentUserSeries.setName("You");
        barChart.getData().add(currentUserSeries);

        XYChart.Series series = new XYChart.Series();
        series.setName("Your Friends");

        Set<User> following = this.userRetrieve.getUserFollow(true, activeUser.getID());
        ArrayList<Object> tempList = new ArrayList<>(following);
        Iterator<Object> iterator = tempList.iterator();
        while (iterator.hasNext()) {

            LinkedHashMap user = (LinkedHashMap) iterator.next();
            XYChart.Data xyChart = new XYChart.Data(user.get("name"), stringToDouble((String) user.get("points")));
            series.getData().add(xyChart);
        }

        barChart.getData().addAll(series);
    }
}
