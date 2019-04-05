package client.UI;

import static client.Service.AchievementGenerator.achNotification;
import static client.Service.AchievementGenerator.giveUserAch;
import static client.UI.RootController.addPointsUser;
import static client.UI.RootController.stringToPoints;
import static java.lang.Integer.parseInt;

import client.Service.MyStage;
import client.Service.UserSession;
import client.model.*;
import client.retrieve.EmissionsRetrieve;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TransportController implements Initializable {

    private static User currentUser = UserSession.getInstance().getCurrentUser();

    public Label transportChoice;

    @FXML
    public Label transport1;
    public Label transport2;
    public Label transport3;
    public Label transport4;
    public Label actionDone;
    public Label pointMessage;
    public Label Labelfield1;
    public Label Labelfield2;
    public Label Labelfield3;

    public TextField field1;
    public TextField field2;
    public TextField field3;

    public TextArea actionDescription;

    public TextArea description;

    private Stage currentStage = MyStage.getInstance();

    private EmissionsRetrieve emissionsRetrieve;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Tooltip tooltipairplane = new Tooltip();
        tooltipairplane.setText("Find out here the negative impacts of aviation on our environment: https://en.wikipedia.org/wiki/Environmental_impact_of_aviation");
        transport1.setTooltip(tooltipairplane);

        final Tooltip tooltipbike = new Tooltip();
        tooltipbike.setText("Read more about the effect of bikes on our environment here: https://www.tmr.qld.gov.au/Travel-and-transport/Cycling/Benefits.aspx");
        transport2.setTooltip(tooltipbike);

        final Tooltip tooltiptrain = new Tooltip();
        tooltiptrain.setText("Read why trains are better for the environment here: https://www.nationalrail.com/4-reasons-taking-train-better-environment/");
        transport3.setTooltip(tooltiptrain);
        this.emissionsRetrieve = new EmissionsRetrieve();
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

    public String getField1Text() {
        return field1.getText();
    }

    /**
     * Method to get the filled in info and parse it into an int.
     * @return int points
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

    /**
     * Method to get the filled in info and parse it into an int.
     * @return int points
     */
    public Integer getIntField2() {
        try {

            System.out.print(true);
            return parseInt(getField2Text());
        } catch (NumberFormatException e) {

            return 0;
        }
    }

    public String getField3Text() {
        return field3.getText();
    }

    /**
     * Method to call other methods to get FlightEmission and to add points to the user.
     */
    public void getFlightEmission() {

        FlightEmission fm = this.emissionsRetrieve.getFlightEmission(getField1Text(), getField2Text());
        System.out.println(fm.getCarbon());

        addPointsUser(stringToPoints(fm.getCarbon()));
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        displayInformation(fm, stringToPoints(fm.getCarbon()));
    }

    /**
     * Method to call other methods to get VehicleEmission and to add points to the user.
     */
    public void getVehicleEmission() {

        VehicleEmission vm = this.emissionsRetrieve.getVehicleEmission(getIntField1(), getIntField2() * 60, getField3Text());
        System.out.println(vm.getCarbon());

        addPointsUser(stringToPoints(vm.getCarbon()));
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        displayInformation(vm, stringToPoints(vm.getCarbon()));
    }

    /**
     * Method to call other methods to get EnergyEmission and to add points to the user.
     */
    public void getTrainCarEmission() {

        VehicleEmission vm = this.emissionsRetrieve.getVehicleEmission(getIntField1(), getIntField2() * 60, getField3Text());
        TrainEmission tm = this.emissionsRetrieve.getTrainEmission(getIntField1(), getIntField2() * 60);
        int carPoints = stringToPoints(vm.getCarbon());
        int trainPoints = stringToPoints(tm.getCarbon());
        int totalPoints = carPoints - trainPoints;

        addPointsUser(totalPoints);
        Achievement newAch = giveUserAch(currentUser);
        achNotification(newAch, currentStage);
        displayInformation(tm, totalPoints);
    }

    /**
     * Changes the labels and fields to correspond with the selected option.
     *
     * @param event The event which activated this method (Mouseclick for this method)
     */
    @SuppressWarnings("Duplicates")
    public void selectFlightEmission(MouseEvent event) {
        transportChoice = RootController.selectOption(event, transportChoice);
        clearFields();

        Labelfield1.setVisible(true);
        field1.setVisible(true);
        Labelfield2.setVisible(true);
        field2.setVisible(true);

        Labelfield3.setVisible(false);
        field3.setVisible(false);
        Labelfield1.setText("Enter your departure airport");
        Labelfield2.setText("Enter your arrival airport");
    }

    /**
     * Changes the labels and fields to correspond with the selected option.
     *
     * @param event The event which activated this method (Mouseclick for this method)
     */
    @SuppressWarnings("Duplicates")
    public void selectVehicleEmission(MouseEvent event) {
        transportChoice = RootController.selectOption(event, transportChoice);
        clearFields();

        Labelfield1.setVisible(true);
        field1.setVisible(true);
        Labelfield2.setVisible(true);
        field2.setVisible(true);
        Labelfield3.setVisible(true);
        field3.setVisible(true);

        Labelfield1.setText("Enter the distance in km");
        Labelfield2.setText("Enter the duration in minutes");
        Labelfield3.setText("Enter the size of your car");
    }

    /**
     * Changes the labels and fields to correspond with the selected option.
     *
     * @param event The event which activated this method (Mouseclick for this method)
     */
    public void selectTrainCarEmission(MouseEvent event) {
        selectVehicleEmission(event);
        Labelfield3.setVisible(false);
        field3.setVisible(false);
    }

    /**
     * Method to clear all the textfields.
     */
    public void clearFields() {
        field1.setText("");
        field2.setText("");
        field3.setText("");
    }

    /**
     * Method behind the select button to handle the option and calling the appropriate methods for handling it.
     */
    public void getEmission() {
        if (transportChoice == transport1) {
            getFlightEmission();
        } else if (transportChoice == transport2) {
            getVehicleEmission();
        }
        if (transportChoice == transport3) {
            getTrainCarEmission();
        }
    }

    /**
     * Method to display tooltips.
     */
    public static void display() {

        final String msg = "There are so many efficent and environmentally friendly ways to get from one place to another.";
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
