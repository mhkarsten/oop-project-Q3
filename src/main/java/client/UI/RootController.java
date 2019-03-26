package client.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import static client.retrive.UserRetrieve.getUser;
import static client.retrive.UserRetrieve.updateUser;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;

public class RootController implements Initializable {

    @FXML
    public Button actionBtn;
    public Button scoreBtn;
    public Button profileBtn;

    public Button foodBtn;
    public Button achievementBtn;
    public Button myScoreBtn;

    public SplitPane mainPane;
    public AnchorPane sidebarPane;
    public AnchorPane changePane;

    public VBox scoreSidebar;
    public VBox actionSidebar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        actionBtn.setOnAction((ActionEvent evt) -> {
            try {

                openFoodScreen();
            } catch (IOException e) {

                e.printStackTrace();
            }
        });

        scoreBtn.setOnAction((ActionEvent evt) -> {
            try {

                openScoreScreen();
            } catch (IOException e) {

                e.printStackTrace();;
            }
        });
    }

    public void openFoodScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/foodScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openScoreScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/scoreScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openProfileScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/profileScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openCompareScreen() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/compareScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openMyScore() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/scoreScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openTransportScreen() throws  IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/transportScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openEnergyScreen() throws  IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/energyScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public static Label selectOption(MouseEvent event, Label choice) {

        if (choice != null) {

            choice.setBackground(new Background(new BackgroundFill(RED, null, null)));
        }

        Label chosenOption = (Label) event.getSource();

        chosenOption.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(1), null)));
        return chosenOption;
    }

    public static void addPointsUser(int points) {
//      UserSession.getInstance().getUser.getId()
        User[] currentUser = getUser(1L);
        currentUser[0].setPoints(currentUser[0].getPoints() + points);

        updateUser(1L, currentUser[0].getName(), currentUser[0].getPoints());

        System.out.println(currentUser.toString());
    }
}
