package client.FXMLControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;

public class EnergyController implements Initializable {

    private Label energyChoice;

    @FXML
    public Label energy1;
    public Label energy2;
    public Label energy3;
    public Label energy4;

    public CheckBox greenEnergy;
    public CheckBox energySaving;
    public CheckBox energyManagement;

    public TextField field1;
    public TextField field2;
    public TextField field3;
    public TextField field4;

    public void getEnergyPoints() {

    }

    public void selectEnergy(MouseEvent event) {

        if (energyChoice != null) {

            energyChoice.setBackground(new Background(new BackgroundFill(RED, null, null)));
        }

        Label chosenEnergy = (Label) event.getSource();

        chosenEnergy.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(1), null)));
        energyChoice = chosenEnergy;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void displayGreenEnergy() {
        energySaving.setSelected(false);
        energyManagement.setSelected(false);

    }

    public void displayEnergySaving() {
        greenEnergy.setSelected(false);
        energyManagement.setSelected(false);

    }

    public void displayEnergyManagement() {
        greenEnergy.setSelected(false);
        energySaving.setSelected(false);

    }

    public String getField1Text() {
        return field1.getText();
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




}
