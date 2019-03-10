package client.FXMLControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
}
