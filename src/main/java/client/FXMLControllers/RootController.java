package client.FXMLControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    public Button actionBtn;
    public Button scoreBtn;
    public Button profileBtn;

    public Button foodBtn;
    public Button achievementBtn;

    public VBox mainSidebar;
    public VBox scoreSidebar;
    public VBox actionSidebar;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;

    private TranslateTransition openNav2;
    private TranslateTransition closeNav2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        hideAllSliderMenus();

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
        actionSideBarHide();
    }

    public void actionSideBarHide() {

        closeNav.setToX(-(actionSidebar.getWidth()));
        closeNav.play();
    }

    public void actionHover() {
        scoreSidebarHide();

        if ((actionSidebar.getTranslateX()) == -(actionSidebar.getWidth()) ) {

            openNav.play();
        } else {

            actionSideBarHide();
        }
    }

    public void scoreSidebarHide() {

        closeNav2.setToX(-(scoreSidebar.getWidth()));
        closeNav2.play();
    }

    public void scoreHover() {
        actionSideBarHide();

        if ((scoreSidebar.getTranslateX()) == -(scoreSidebar.getWidth()) ) {

            openNav2.play();
        } else {

            scoreSidebarHide();
        }
    }

    public void openFoodScreen() {


    }

    public void openScoreScreen() {


    }
}
