package client.ui;

import static client.service.AchievementGenerator.achNotification;
import static client.service.AchievementGenerator.giveUserAch;
import static client.ui.RootController.addPointsUser;
import static client.ui.RootController.stringToPoints;
import static java.lang.Integer.parseInt;

import client.model.Achievement;
import client.model.Emission;
import client.model.EnergyEmission;
import client.model.User;
import client.retrieve.EmissionsRetrieve;
import client.service.MyStage;
import client.service.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EnergyController implements Initializable {

    @FXML
    public Label energy1;
    public Label energy2;
    public Label energy3;
    public Label energy4;

    public Label labelfield1;
    public Label labelfield2;
    public Label labelfield3;
    public Label labelfield4;

    public Label actionDone;
    public Label pointMessage;

    public TextArea description;

    public TextArea actionDescription;

    public TextField field1;
    public TextField field2;
    public TextField field3;
    public TextField field4;

    private Label energyChoice;

    private User currentUser = UserSession.getInstance().getCurrentUser();
    private Stage currentStage = MyStage.getInstance();

    private EmissionsRetrieve emissionsRetrieve;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Tooltip tooltipsolar = new Tooltip();
        tooltipsolar.setText("Solar panels are known to have positive impacts, but read both sides of the story here! https://www.greenmatch.co.uk/blog/2015/01/impact-of-solar-energy-on-the-environment");
        energy2.setTooltip(tooltipsolar);

        this.emissionsRetrieve = new EmissionsRetrieve();
    }

    public String getField1Text() {
        return field1.getText();
    }

    /**
     * Method to display information about your action.
     * @param em Emission associated with the action
     * @param points points associated with the action
     */
    public void displayInformation(Emission em, int points) {

        actionDone.setText("You have earned " + points + " points for reducing your " + em.getStringName());
        pointMessage.setText("You have caused " + em.getCarbon() + " KG of C02.");
        actionDescription.setText(em.toString());
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
        EnergyEmission em = this.emissionsRetrieve.getEnergyEmission(getIntField1(), getField2Text(), getField3Text(), getIntField4());
        System.out.println(em.getCarbon());

        addPointsUser(stringToPoints(em.getCarbon()));
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        displayInformation(em, stringToPoints(em.getCarbon()));
    }

    /**
     * Method to handle the action of installing solar panels.
     */
    public void getSolarPanelEmission() {
        int solarPoints = getIntField1() * 100;

        addPointsUser(solarPoints);
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        actionDescription.setText("You are saving the environment by buying solar panels");
        actionDone.setText("You have installed solar panels");
        pointMessage.setText("You have earned " + solarPoints + " points!");
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

        labelfield1.setVisible(true);
        field1.setVisible(true);
        labelfield2.setVisible(true);
        field2.setVisible(true);
        labelfield3.setVisible(true);
        field3.setVisible(true);
        labelfield4.setVisible(true);
        field4.setVisible(true);

        labelfield1.setText("Enter the amount of green energy you use");
        labelfield2.setText("Enter your dishwasher use in KW/H");
        labelfield3.setText("Enter your air conditioner use");
        labelfield4.setText("Enter your monthly gas costs in USD");
    }

    /**
     * Changes the labels and fields to correspond with the selected option.
     *
     * @param event The event which activated this method (Mouseclick for this method)
     */
    public void selectSolarPanelsEmission(MouseEvent event) {
        energyChoice = RootController.selectOption(event, energyChoice);
        clearFields();

        labelfield1.setVisible(true);
        field1.setVisible(true);
        labelfield2.setVisible(false);
        field2.setVisible(false);
        labelfield3.setVisible(false);
        field3.setVisible(false);
        labelfield4.setVisible(false);
        field4.setVisible(false);

        labelfield1.setText("Enter the amount of solar panels you installed");
    }

    /**
     * Method behind the select button to handle the option and calling the appropriate methods for handling it.
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

    /**
     * Method to display tooltips.
     */
    public static void display() {

        final String msg = "There are many ways to reduce your energy consumption to help the environment.";
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Help");
        window.setMinWidth(250);

        Button closeButton = new Button("Got It!");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().add(new Text(msg));
        layout.getChildren().add(closeButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }


}
