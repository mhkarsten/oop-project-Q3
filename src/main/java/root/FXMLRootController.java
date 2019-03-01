package root;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FXMLRootController implements Initializable {
    @FXML
    private Label label;
    public Label lblLink;
    public Button btnHospital;
    public Button btnHelp;

    public VBox vbSidebarMain;
    public VBox vbHospitalSidebar;
    public VBox vbHelpSidebar;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;

    private TranslateTransition openNav2;
    private TranslateTransition closeNav2;
    private TranslateTransition closeFastNav2;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnIcons8_Click(ActionEvent event) {
        lblLink.setText("https://icons8.com to get free icons used in this app.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openNav = new TranslateTransition(Duration.millis(100), vbHospitalSidebar);
        openNav.setToX(vbHospitalSidebar.getTranslateX()-vbHospitalSidebar.getWidth());
        closeNav = new TranslateTransition(Duration.millis(100), vbHospitalSidebar);
        closeFastNav = new TranslateTransition(Duration.millis(.1), vbHospitalSidebar);

        openNav2 = new TranslateTransition(Duration.millis(100), vbHelpSidebar);
        openNav2.setToX(vbHelpSidebar.getTranslateX()-vbHelpSidebar.getWidth());
        closeNav2 = new TranslateTransition(Duration.millis(100), vbHelpSidebar);
        closeFastNav2 = new TranslateTransition(Duration.millis(.1), vbHelpSidebar);

        btnHospital.setOnAction((ActionEvent evt) -> {
            btnHospitalHover();
        });

        btnHelp.setOnAction((ActionEvent evt) -> {
            btnHelpHover();
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                closeFastNav.setToX(-(vbHospitalSidebar.getWidth()));
                closeFastNav.play();
                closeFastNav2.setToX(-(vbHelpSidebar.getWidth()));
                closeFastNav2.play();
            }
        });
    }

    public void hideAllSliderMenus(){
        hospitalSideBarHide();
        helpSidebarHide();
    }

    public void hospitalSideBarHide() {
        btnHospital.getStyleClass().remove("sidebar-button-active");
        btnHospital.getStyleClass().add("sidebar-button");
        closeNav.setToX(-(vbHospitalSidebar.getWidth()));
        closeNav.play();
    }

    public void btnHospitalHover() {
        helpSidebarHide();

        if ((vbHospitalSidebar.getTranslateX()) == -(vbHospitalSidebar.getWidth()) ) {
            btnHospital.getStyleClass().remove("sidebar-button");
            btnHospital.getStyleClass().add("sidebar-button-active");
            openNav.play();
        } else {
            hospitalSideBarHide();
        }
    }

    public void helpSidebarHide() {
        btnHelp.getStyleClass().remove("sidebar-button-active");
        btnHelp.getStyleClass().add("sidebar-button");
        closeNav2.setToX(-(vbHelpSidebar.getWidth()));
        closeNav2.play();
    }

    public void btnHelpHover() {
        hospitalSideBarHide();

        if ((vbHelpSidebar.getTranslateX()) == -(vbHelpSidebar.getWidth()) ) {
            btnHelp.getStyleClass().remove("sidebar-button");
            btnHelp.getStyleClass().add("sidebar-button-active");
            openNav2.play();
        } else {
            helpSidebarHide();
        }
    }


}
