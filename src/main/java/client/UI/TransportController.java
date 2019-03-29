package client.UI;

import client.retrive.EmissionsRetrieve;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import server.model.FlightEmission;
import server.model.VehicleEmission;

import static client.UI.RootController.addPointsUser;
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

    public int calculatePoints(String points) {
        return parseInt(points.split("\\.")[0]) / 10;
    }

    public void getFlightEmission() {
        FlightEmission fm = EmissionsRetrieve.getFlightEmission(getField1Text(), getField2Text());
        System.out.println(fm.getCarbon());
        addPointsUser(calculatePoints(fm.getCarbon()));
    }

    public void getVehicleEmission() {
        VehicleEmission vm = EmissionsRetrieve.getVehicleEmission(getIntField1(), getIntField2() * 60, getField3Text());
        System.out.println(vm.getCarbon());
        addPointsUser(calculatePoints(vm.getCarbon()));
    }

    public void selectFlightEmission(MouseEvent event) {
        transportChoice = RootController.selectOption(event, transportChoice);

        Labelfield1.setVisible(true);
        field1.setVisible(true);
        Labelfield2.setVisible(true);
        field2.setVisible(true);

        Labelfield3.setVisible(false);
        field3.setVisible(false);
        Labelfield1.setText("Enter your departure airport");
        Labelfield2.setText("Enter your arrival airport");
    }

    public void selectVehicleEmission(MouseEvent event) {
        transportChoice = RootController.selectOption(event, transportChoice);

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

    public void getEmission() {
        if(transportChoice == transport1) {
            getFlightEmission();
        }
        else if(transportChoice == transport2) {
            getVehicleEmission();
        }
    }
}
