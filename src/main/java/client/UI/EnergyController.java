package client.UI;

import client.Service.MyStage;
import client.Service.UserSession;
import client.model.Achievement;
import client.model.User;
import javafx.stage.Stage;
import server.model.EnergyEmission;
import client.retrive.EmissionsRetrieve;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static client.Service.AchievementGenerator.achNotification;
import static client.Service.AchievementGenerator.giveUserAch;
import static client.UI.RootController.addPointsUser;
import static client.UI.RootController.stringToPoints;
import static java.lang.Integer.parseInt;

public class EnergyController implements Initializable {

    private Label energyChoice;

    @FXML
    public Label energy1;
    public Label energy2;
    public Label energy3;
    public Label energy4;

    public Label Labelfield1;
    public Label Labelfield2;
    public Label Labelfield3;
    public Label Labelfield4;

    public TextArea description;

    public TextField field1;
    public TextField field2;
    public TextField field3;
    public TextField field4;

    private User currentUser = UserSession.getInstace().getCurrentUser();
    private Stage currentStage = MyStage.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public String getField1Text() {
        return field1.getText();
    }

    /**
     * Method to extract an Integer from the textfield with label Textfield1.
     *
     * @return an integer from a string if not compatible returns 0
     */
    public Integer getIntField1() {
        try {
            return parseInt(getField1Text());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getField2Text() {
        return field2.getText();
    }

    public String getField3Text() {
        return field3.getText();
    }

    public String getField4Text() {
        return field4.getText();
    }

    /**
     * Method to extract an Integer from the textfield with label Textfield4.
     *
     * @return an integer from a string if not compatible returns 0
     */
    public Integer getIntField4() {
        try {
            return parseInt(getField4Text());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Method to call other methods to get EnergyEmission and to add points to the user.
     */
    public void getEnergyEmission() {
        EnergyEmission em = EmissionsRetrieve.getEnergyEmission(getIntField1(), getField2Text(), getField3Text(), getIntField4());
        System.out.println(em.getCarbon());

        addPointsUser(stringToPoints(em.getCarbon()));
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
    }

    public void getSolarPanelEmission() {
        int SolarPanels = getIntField1() * 100;

        addPointsUser(SolarPanels);
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
    }

    /**
     * Changes the labels and fields to correspond with the selected option.
     *
     * @param event The event which activated this method (Mouseclick for this method)
     */
    @SuppressWarnings("Duplicates")
    public void selectEnergyEmission(MouseEvent event) {
        energyChoice = RootController.selectOption(event, energyChoice);
        clearFields();

        Labelfield1.setVisible(true);
        field1.setVisible(true);
        Labelfield2.setVisible(true);
        field2.setVisible(true);
        Labelfield3.setVisible(true);
        field3.setVisible(true);
        Labelfield4.setVisible(true);
        field4.setVisible(true);

        Labelfield1.setText("Enter the amount of green energy you use");
        Labelfield2.setText("Enter your dishwasher use in KW/H");
        Labelfield3.setText("Enter your air conditioner use");
        Labelfield4.setText("Enter your monthly gas costs in USD");
    }

    public void selectSolarPanelsEmission(MouseEvent event) {
        energyChoice = RootController.selectOption(event, energyChoice);
        clearFields();

        Labelfield1.setVisible(true);
        field1.setVisible(true);
        Labelfield2.setVisible(false);
        field2.setVisible(false);
        Labelfield3.setVisible(false);
        field3.setVisible(false);
        Labelfield4.setVisible(false);
        field4.setVisible(false);

        Labelfield1.setText("Enter the amount of solar panels you installed");
    }

    /**
     * Method behind the select button to choose what option has to be executed.
     */
    public void getEmission() {
        if (energyChoice == energy1) {
            getEnergyEmission();
        }
        if (energyChoice == energy2) {
            getSolarPanelEmission();
        }
    }

    /**
     * Method to clear all the textfields.
     */
    public void clearFields() {
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
    }

}
