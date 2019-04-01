package client.UI;

import client.Service.UserSession;
import client.model.Meal;
import client.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

import static client.retrive.FoodRetrieve.*;
import static client.retrive.UserRetrieve.*;

/**
 * The type Food controller.
 */
public class FoodController implements Initializable {

    private Label mealChoice;

    /**
     * The Meal 1.
     */
    @FXML
    public Label meal1;
    public Label meal2;
    public Label meal3;
    public Label meal4;
    public Label mealBoxText;

    public CheckBox veganOpt;
    public CheckBox vegOpt;
    public CheckBox meatOpt;

    public Button upBtn;
    public Button downBtn;

    private int mealOffset;
    private ArrayList<Meal> meatMeals;
    private ArrayList<Meal> veganMeals;
    private ArrayList<Meal> vegetarianMeals;
    private User currentUser = UserSession.getInstace().getCurrentUser();
    /**
     * Gets selected category.
     *
     * @return the selected category
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        meal1.setText(getRandomMeal()[0].getStrMeal());
        meal2.setText(getRandomMeal()[0].getStrMeal());
        meal3.setText(getRandomMeal()[0].getStrMeal());
        meal4.setText(getRandomMeal()[0].getStrMeal());

        meatMeals = getAllMeatMeals();
        veganMeals = getMealCategory("Vegan");
        vegetarianMeals = getMealCategory("Vegetarian");

        upBtn.setOnAction(event -> changeMeals(upBtn));
        downBtn.setOnAction((event -> changeMeals(downBtn)));
    }

    /**
     * Gets selected category.
     *
     * @return the selected category
     */
    public ArrayList<Meal> getSelectedCategory() {

        if (vegOpt.isSelected()) {

            return vegetarianMeals;
        } else if (meatOpt.isSelected()) {

            return meatMeals;
        } else if (veganOpt.isSelected()) {

            return veganMeals;
        }
        return null;
    }

    /**
     * Sets meal strings.
     *
     * @param mealCategory the meal category
     * @param offset       the offset
     */
    public void setMealStrings(ArrayList<Meal> mealCategory, int offset) {

        HashMap<String, Label> varNameMap = new HashMap<>();
        varNameMap.put("meal1", meal1);
        varNameMap.put("meal2", meal2);
        varNameMap.put("meal3", meal3);
        varNameMap.put("meal4", meal4);

        if (mealCategory.size() < 4) {

            int offsetAddition = 0;

            for (int i = 1; i < mealCategory.size() + 1; i++) {

                varNameMap.get("meal" + i).setText(mealCategory.get(offset + offsetAddition).getStrMeal());

                offsetAddition++;
            }

            for (int i = mealCategory.size() + 1; i <= 4; i++) {

                varNameMap.get("meal" + i).setText("");
            }
        } else {

            meal1.setText(mealCategory.get(offset).getStrMeal());
            meal2.setText(mealCategory.get(offset + 1).getStrMeal());
            meal3.setText(mealCategory.get(offset + 2).getStrMeal());
            meal4.setText(mealCategory.get(offset + 3).getStrMeal());
        }
    }

    /**
     * Display veg meals.
     */
    public void displayVegMeals() {

        meatOpt.setSelected(false);
        veganOpt.setSelected(false);

        setMealStrings(vegetarianMeals, 0);
    }

    /**
     * Display vegan meals.
     */
    public void displayVeganMeals() {

        meatOpt.setSelected(false);
        vegOpt.setSelected(false);

        setMealStrings(veganMeals, 0);
    }

    /**
     * Display meat meals.
     */
    public void displayMeatMeals() {

        vegOpt.setSelected(false);
        veganOpt.setSelected(false);

        setMealStrings(meatMeals, 0);
    }

    /**
     * Select meal.
     *
     * @param event the event
     */
    public void selectMeal(MouseEvent event) {
        mealChoice = RootController.selectOption(event, mealChoice);
    }

    /**
     * Gets meal points.
     */
    public void getMealPoints() {



        if (veganOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 100);

            mealBoxText.setText("You have earned 100 pts for eating a vegan meal!");

            updateUserPoints(currentUser.getPoints());

            System.out.println(currentUser.toString());

        } else if (meatOpt.isSelected()) {

            mealBoxText.setText("You have earned 0 pts for eating a meal with meat!");

        } else if (vegOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 50);

            mealBoxText.setText("You have earned 50 pts for eating a vegetarian meal!");

            System.out.println(currentUser.toString());

            updateUserPoints(currentUser.getPoints());
        } else {

            currentUser.setPoints(currentUser.getPoints() + 25);

            mealBoxText.setText("You have selected a random meal, and have been awarded 25 points!");

            updateUserPoints(currentUser.getPoints());
        }
    }

    /**
     * Change meals.
     *
     * @param button the button
     */
    public void changeMeals(Button button) {

        if (button == downBtn) {

            mealOffset = mealOffset + 4;
            setMealStrings(getSelectedCategory(), mealOffset);
        } else if (button == upBtn) {

            if (mealOffset <= 4) {

                mealOffset = mealOffset - 4;
                setMealStrings(getSelectedCategory(), mealOffset);
            }
        }
    }
}