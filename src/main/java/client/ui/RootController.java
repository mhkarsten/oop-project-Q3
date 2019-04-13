package client.ui;

import static java.lang.Integer.parseInt;
import static javafx.scene.paint.Color.WHITE;

import client.model.User;
import client.retrieve.UserRetrieve;
import client.service.UserSession;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The type Root controller.
 */
public class RootController implements Initializable {

    private static User currentUser = UserSession.getInstance().getCurrentUser();

    /**
     * The Profile.
     */
    @FXML
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

    private UserRetrieve userRetrieve;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userRetrieve = new UserRetrieve();

        try {

            openProfileScreen();
        } catch (IOException e) {

            System.out.println("There was an issue opening the profile screen.");
        }

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(score);
        buttons.add(compare);
        buttons.add(food);
        buttons.add(transport);
        buttons.add(energy);
        buttons.add(achievement);

        buttons.forEach(btn -> btn.setOnMouseClicked(mouseEvent -> progressBar()));
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

    /**
     * Open achievement screen.
     *
     * @throws IOException the io exception
     */
    public void openAchievementScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/AchievementUI.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void progressBar() {

        Timeline rowMove = new Timeline();
        rowMove.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(2),
                new KeyValue(stretchbutton.scaleXProperty(), 200)
            )
        );
    }

    /**
     * Method to highlight and remove the highlight from selected/ unselected option.
     *
     * @param event  The event from which you get which label has been pressed
     * @param choice The Label that was highlighted before the change
     * @return label
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

    /**
     * String to points int.
     *
     * @param points the points
     * @return the int
     */
    public static int stringToPoints(String points) {
        return parseInt(points.split("\\.")[0]);
    }

    public static Double stringToDouble(String points) {
        return Double.valueOf(points);
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
