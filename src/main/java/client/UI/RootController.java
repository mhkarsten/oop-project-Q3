package client.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import client.Service.UserSession;
import client.model.User;
import client.retrive.UserRetrieve;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import static client.retrive.UserRetrieve.*;
import static java.lang.Integer.parseInt;
import static javafx.scene.paint.Color.WHITE;

public class RootController implements Initializable {

    @FXML
    public Button profile;
    public Button action;
    public Button stretchbutton;
    public Button score;
    public Button compare;
    public Button food;
    public Button transport;
    public Button energy;
    public Button achievement;
    public SplitPane mainPane;
    public AnchorPane sidebarPane;
    public AnchorPane changePane;
    public BarChart<String, Number> barChart;

    private static User currentUser = UserSession.getInstance().getCurrentUser();
    private UserRetrieve userRetrieve;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userRetrieve = new UserRetrieve();

        action.setOnAction((ActionEvent evt) -> {
            try {
                openFoodScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        score.setOnAction((ActionEvent evt) -> {
            try {

                openProfileScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Method to open the FoodScreen on the side pane.
     *
     * @throws IOException Exception if the screen fails to be loaded
     */
    public void openFoodScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/foodScreen.fxml"));
        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    /**
     * Method to open the ProfileScreen on the side pane.
     *
     * @throws IOException Exception if the screen fails to be loaded
     */
    public void openProfileScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/profileScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    /**
     * Method to open the CompareScreen on the side pane.
     *
     * @throws IOException Exception if the screen fails to be loaded
     */
    public void openCompareScreen() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/compareScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    /**
     * Method to open the TransportScreen on the side pane.
     *
     * @throws IOException Exception if the screen fails to be loaded
     */
    public void openTransportScreen() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/transportScreen.fxml"));

        changePane = newScreen.load();
        mainPane.getItems().set(1, changePane);
    }

    /**
     * Method to open the EnergyScreen on the side pane.
     *
     * @throws IOException Exception if the screen fails to be loaded
     */
    public void openEnergyScreen() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/energyScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openAchievementScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/AchievementUI.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void moveButtons() {
        Timeline rowMove = new Timeline();

        Timeline fade = new Timeline();
        fade.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(1),
                new KeyValue(action.opacityProperty(), 0),
                new KeyValue(profile.opacityProperty(), 0),
                new KeyValue(score.opacityProperty(), 0),
                new KeyValue(compare.opacityProperty(), 0),
                new KeyValue(food.opacityProperty(), 0),
                new KeyValue(transport.opacityProperty(), 0),
                new KeyValue(energy.opacityProperty(), 0),
                new KeyValue(achievement.opacityProperty(), 0)
            )
        );
        rowMove.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(2),
                new KeyValue(stretchbutton.scaleXProperty(), 200)
            )
        );

        fade.play();
        fade.setOnFinished(e -> rowMove.play());
        rowMove.setOnFinished(e -> fade.stop());
        rowMove.setOnFinished(e -> rowMove.stop());
    }

    public void moveBackButton() {
        Timeline rowMove = new Timeline();

        Timeline fade = new Timeline();
        fade.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(1),
                new KeyValue(action.opacityProperty(), 1),
                new KeyValue(profile.opacityProperty(), 1),
                new KeyValue(score.opacityProperty(), 1),
                new KeyValue(compare.opacityProperty(), 1),
                new KeyValue(food.opacityProperty(), 1),
                new KeyValue(transport.opacityProperty(), 1),
                new KeyValue(energy.opacityProperty(), 1),
                new KeyValue(achievement.opacityProperty(), 1)
            )
        );
        rowMove.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(2),
                new KeyValue(stretchbutton.scaleXProperty(), 1)
            )
        );

        fade.play();
        fade.setOnFinished(e -> rowMove.play());
        rowMove.setOnFinished(e -> fade.stop());
        rowMove.setOnFinished(e -> rowMove.stop());
    }

    public void btn(ActionEvent event) {

        Set<User> following = this.userRetrieve.getUserFollow(true, currentUser.getID());

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);

        xAxis.setLabel("Username");
        yAxis.setLabel("Points");

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(currentUser.getName(), currentUser.getPoints()));

        Iterator<User> iterator = following.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            XYChart.Data xyChart = new XYChart.Data(user.getName(), user.getPoints());
            series.getData().add(xyChart);
        }

        barChart.getData().addAll(series);
    }

    public int getMax(Set<User> users) {

        int points = UserSession.getInstance().getCurrentUser().getPoints();
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {

            int temp = iterator.next().getPoints();
            if (temp > points) {
                points = temp;
            }
        }

        return points;
    }


    /**
     * Method to highlight and remove the highlight from selected/ unselected option.
     *
     * @param event  The event from which you get which label has been pressed
     * @param choice The Label that was highlighted before the change
     * @return
     */
    public static Label selectOption(MouseEvent event, Label choice) {

        Color redVbox = Color.rgb(239, 154, 154);

        if (choice != null) {

            choice.setBackground(new Background(new BackgroundFill(redVbox, null, null)));
        }

        Label chosenOption = (Label) event.getSource();

        chosenOption.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(1), null)));
        return chosenOption;
    }

    public static int stringToPoints(String points) {
        return parseInt(points.split("\\.")[0]);
    }

    /**
     * Method to make calls to update the points of the current user.
     *
     * @param points The amount of points that should be added to the user
     */
    public static void addPointsUser(int points) {

        int pointsToUpdate = currentUser.getPoints();
        pointsToUpdate += points;
        UserSession.getInstance().getCurrentUser().setPoints(pointsToUpdate);

        UserRetrieve userRetrieve = new UserRetrieve();
        userRetrieve.addGenericFeat(UserSession.getInstance().getCurrentUser().getID(), points);
    }
}
