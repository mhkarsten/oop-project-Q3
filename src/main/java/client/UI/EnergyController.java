package client.UI;

import client.Service.MyStage;
import client.Service.UserSession;
import client.model.Achievement;
import client.model.Emission;
import client.model.User;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import client.model.EnergyEmission;
import client.retrieve.EmissionsRetrieve;
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

    public TextArea actionDescription;
    public Label actionDone;
    public Label pointMessage;

    public TextField field1;
    public TextField field2;
    public TextField field3;
    public TextField field4;

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

    public void getSolarPanelEmission() {
        int SolarPanels = getIntField1() * 100;

        addPointsUser(SolarPanels);
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        actionDescription.setText("You are saving the environment by buying solar panels");
        actionDone.setText("You have installed solar panels");
        pointMessage.setText("You have earned " + SolarPanels + " points!");
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
    public static void display() {

        String msg = "There are many ways to reduce your energy consumption to help the environment.";
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
