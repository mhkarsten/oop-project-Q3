package client.ui;

import client.model.Feat;
import client.model.User;
import client.retrieve.FeatRetrieve;
import client.service.UserSession;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    public Label userName;
    public Label userID;
    public Label userPoints;
    public ListView featListView;

    public LineChart<Integer, Integer> lineChart;

    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentUser = UserSession.getInstance().getCurrentUser();

        userName.setText("User Name: " + currentUser.getName());
        userID.setText("User ID: " + currentUser.getID());
        userPoints.setText("User Points: " + currentUser.getPoints());

        //Setting up the list of users the active user is following
        FeatRetrieve featRetrive = new FeatRetrieve();

        featListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> listViewContents = featListView.getItems();
        ArrayList<Feat> tempArray = null;
        try {
            tempArray = featRetrive.getUserFeats(currentUser.getID());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        tempArray.forEach(feat -> {

            listViewContents.add("You have earned " + feat.getPoints() + " points for an activity.");
        });

        loadLineGraph();
    }

    /**
     * Loads the graph data by getting the user feats of the current user.
     */
    public void loadLineGraph() {

        FeatRetrieve retrive = new FeatRetrieve();
        ArrayList<Feat> tempArray = retrive.getUserFeats(currentUser.getID());

        XYChart.Series series = new XYChart.Series();
        series.setName("Your Saved C02");

        int nextGraphX = 0;

        for (int i = 0; i < tempArray.size(); i++) {

            nextGraphX = nextGraphX + tempArray.get(i).getPoints();

            XYChart.Data entry = new XYChart.Data(i, nextGraphX);
            series.getData().add(entry);
        }

        lineChart.getData().addAll(series);
    }
}
