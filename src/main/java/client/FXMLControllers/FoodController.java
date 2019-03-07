package client.FXMLControllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static client.foodAPI.FoodAPI.getRandomMeal;

public class FoodController {

    @FXML
    private Label meal1;
    @FXML
    private Label meal2;
    @FXML
    private Label meal3;
    @FXML
    private Label meal4;


    @FXML
    private Circle selectCircle1;
    @FXML
    private Circle selectCircle2;
    @FXML
    private Circle selectCircle3;
    @FXML
    private Circle selectCircle4;

    @FXML
    private CheckBox veganOpt;
    @FXML
    private CheckBox vegOpt;
    @FXML
    private CheckBox meatOpt;

    @FXML
    private Button mealSelected;

    public void selectMeal(MouseEvent event) {

        event.getSource();

    }

    private ObservableSet<CheckBox> selectedBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedBoxes = FXCollections.observableSet();

    private IntegerBinding numBoxesSelected = Bindings.size(selectedBoxes);

    private final int maxNumSelected =  1;



//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//        meal1.setText(getRandomMeal()[0].getStrMeal());
//        meal2.setText(getRandomMeal()[0].getStrMeal());
//        meal3.setText(getRandomMeal()[0].getStrMeal());
//        meal4.setText(getRandomMeal()[0].getStrMeal());
//
//    }

    public void configureMealBoxes(CheckBox box) {

        if(box.isSelected()) {

            selectedBoxes.add(box);
        } else {

            unselectedBoxes.add(box);
        }

        box.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {

                unselectedBoxes.remove(box);
                selectedBoxes.add(box);
            } else {

                selectedBoxes.remove(box);
                unselectedBoxes.add(box);
            }
        });
    }

    public void changeMeals(ActionEvent event) {

        event.getID();


    }
}
