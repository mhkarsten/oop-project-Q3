package client.FXMLControllers;

import client.foodAPI.Meal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static client.foodAPI.FoodAPI.*;

public class FoodController implements Initializable {

    @FXML
    public Label meal1;
    public Label meal2;
    public Label meal3;
    public Label meal4;

    public Circle selectCircle1;
    public Circle selectCircle2;
    public Circle selectCircle3;
    public Circle selectCircle4;

    public CheckBox veganOpt;
    public CheckBox vegOpt;
    public CheckBox meatOpt;

    public Button mealSelected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        meal1.setText(getRandomMeal()[0].getStrMeal());
        meal2.setText(getRandomMeal()[0].getStrMeal());
        meal3.setText(getRandomMeal()[0].getStrMeal());
        meal4.setText(getRandomMeal()[0].getStrMeal());
    }


    public void setMealStrings(ArrayList<Meal[]> mealCategory, int offset) {

        HashMap<String, Label> varNameMap = new HashMap<>();
        varNameMap.put("meal1", meal1);
        varNameMap.put("meal2", meal2);
        varNameMap.put("meal3", meal3);
        varNameMap.put("meal4", meal4);

        if (mealCategory.size() < 4) {

            int offsetAddition = 0;

            for (int i = 1; i < mealCategory.size() + 1; i++) {

                varNameMap.get("meal" + i).setText(mealCategory.get(offset + offsetAddition)[0].getStrMeal());

                offsetAddition++;
            }

            for (int i = mealCategory.size() + 1; i <= 4; i++) {

                varNameMap.get("meal" + i).setText("");
            }
        } else {

            meal1.setText(mealCategory.get(offset)[0].getStrMeal());
            meal2.setText(mealCategory.get(offset + 1)[0].getStrMeal());
            meal3.setText(mealCategory.get(offset + 2)[0].getStrMeal());
            meal4.setText(mealCategory.get(offset + 3)[0].getStrMeal());
        }
    }

    public void displayVegMeals() {

        meatOpt.setSelected(false);
        veganOpt.setSelected(false);

        ArrayList<Meal[]> vegetarianMeals = getMealCategory("Vegetarian");

        setMealStrings(vegetarianMeals, 0);
    }

    public void displayVeganMeals() {

        meatOpt.setSelected(false);
        vegOpt.setSelected(false);

        ArrayList<Meal[]> veganMeals = getMealCategory("Vegan");

        setMealStrings(veganMeals, 0);
    }

    public void displayMeatMeals() {

        vegOpt.setSelected(false);
        veganOpt.setSelected(false);

        ArrayList<Meal[]> meatMeals = getAllMeatMeals();

        setMealStrings(meatMeals, 0);
    }
    
}
