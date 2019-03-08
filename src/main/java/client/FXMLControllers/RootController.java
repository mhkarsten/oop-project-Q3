package client.FXMLControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    public Button actionBtn;
    public Button scoreBtn;
    public Button profileBtn;

    public Button foodBtn;
    public Button achievementBtn;

    public SplitPane mainPane;
    public AnchorPane sidebarPane;
    public AnchorPane changePane;

    public VBox mainSidebar;
    public VBox scoreSidebar;
    public VBox actionSidebar;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    private TranslateTransition openNav2;
    private TranslateTransition closeNav2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        openNav = new TranslateTransition(Duration.millis(100), actionSidebar);
        openNav.setToX(actionSidebar.getTranslateX()-actionSidebar.getWidth());
        closeNav = new TranslateTransition(Duration.millis(100), actionSidebar);

        openNav2 = new TranslateTransition(Duration.millis(100), scoreSidebar);
        openNav2.setToX(scoreSidebar.getTranslateX()-scoreSidebar.getWidth());
        closeNav2 = new TranslateTransition(Duration.millis(100), scoreSidebar);

        scoreBtn.setOnAction((ActionEvent evt) -> actionHover());

        actionBtn.setOnAction((ActionEvent evt) -> scoreHover());
    }

    public void hideAllSliderMenus(){

        scoreSidebarHide();
        actionSidebarHide();
    }

    public void actionSidebarHide() {

        closeNav.setToX(-(actionSidebar.getWidth()));
        closeNav.play();
    }

    public void actionHover() {
        scoreSidebarHide();

        if ((actionSidebar.getTranslateX()) == -(actionSidebar.getWidth()) ) {

            openNav.play();
        } else {

            actionSidebarHide();
        }
    }

    public void scoreSidebarHide() {

        closeNav2.setToX(-(scoreSidebar.getWidth()));
        closeNav2.play();
    }

    public void scoreHover() {
        actionSidebarHide();

        if ((scoreSidebar.getTranslateX()) == -(scoreSidebar.getWidth()) ) {

            openNav2.play();
        } else {

            scoreSidebarHide();
        }
    }

    public void openFoodScreen(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/foodScreen.fxml"));
        changePane = loader.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openScoreScreen(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scoreScreen.fxml"));
        changePane = loader.load();

        mainPane.getItems().set(1, changePane);
    }
}
