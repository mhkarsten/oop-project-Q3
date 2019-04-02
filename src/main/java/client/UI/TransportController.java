package client.UI;

import client.retrive.EmissionsRetrieve;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.model.FlightEmission;
import server.model.TrainEmission;
import server.model.VehicleEmission;

import static client.UI.RootController.addPointsUser;
import static client.UI.RootController.stringToPoints;
import static java.lang.Integer.parseInt;

public class TransportController {

    public Label transportChoice;

    @FXML
    public Label transport1;
    public Label transport2;
    public Label transport3;
    public Label transport4;

    public Label Labelfield1;
    public Label Labelfield2;
    public Label Labelfield3;

    public TextField field1;
    public TextField field2;
    public TextField field3;

    public TextArea description;

    public String getField1Text() {
        return field1.getText();
    }

    public Integer getIntField1() {
        try {
            return parseInt(getField1Text());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getField2Text() {
        return field2.getText();
    }

    public Integer getIntField2() {
        try {
            System.out.print(true);
            return parseInt(getField2Text());
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getField3Text() {
        return field3.getText();
    }

    public void getFlightEmission() {
        FlightEmission fm = EmissionsRetrieve.getFlightEmission(getField1Text(), getField2Text());
        System.out.println(fm.getCarbon());
        addPointsUser(stringToPoints(fm.getCarbon()));
    }

    public void getVehicleEmission() {
        VehicleEmission vm = EmissionsRetrieve.getVehicleEmission(getIntField1(), getIntField2() * 60, getField3Text());
        System.out.println(vm.getCarbon());
        addPointsUser(stringToPoints(vm.getCarbon()));
    }

    public void getTrainCarEmission() {
        VehicleEmission vm = EmissionsRetrieve.getVehicleEmission(getIntField1(), getIntField2() * 60, getField3Text());
        TrainEmission tm = EmissionsRetrieve.getTrainEmission(getIntField1(), getIntField2() * 60);
        int CarEmission = stringToPoints(vm.getCarbon());
        int TrainEmission = stringToPoints(tm.getCarbon());
        int TrainCarEmission = CarEmission - TrainEmission;

        addPointsUser(TrainCarEmission);
    }

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

    public void selectTrainCarEmission(MouseEvent event) {
        selectVehicleEmission(event);
        Labelfield3.setVisible(false);
        field3.setVisible(false);
    }

    public void clearFields() {
        field1.setText("");
        field2.setText("");
        field3.setText("");
    }

    public void getEmission() {
        if(transportChoice == transport1) {
            getFlightEmission();
        }
        else if(transportChoice == transport2) {
            getVehicleEmission();
        }
        if(transportChoice == transport3) {
            getTrainCarEmission();
        }
    }

    public static void display() {

        String msg = "There are so many efficent and environmentally friendly ways to get from one place to another.";
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
