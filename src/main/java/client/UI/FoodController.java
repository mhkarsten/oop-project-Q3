package client.UI;

import client.Service.MyStage;
import client.Service.UserSession;
import client.model.Achievement;
import client.model.Meal;
import client.model.User;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

import static client.Service.AchievementGenerator.achNotification;
import static client.Service.AchievementGenerator.giveUserAch;
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
    public Label mealBoxText;
    public Label searchStatus;
    public Label searchMealName;

    public CheckBox veganOpt;
    public CheckBox vegOpt;
    public CheckBox meatOpt;
    public CheckBox localProduceBtn;

    public Button searchBtn;
    public Button selectMeal;

    public ListView mealView;

    public TextField searchInput;

    private ArrayList<Meal> meatMeals;
    private ArrayList<Meal> veganMeals;
    private ArrayList<Meal> vegetarianMeals;
    private User currentUser = UserSession.getInstace().getCurrentUser();
    private Stage currentStage = MyStage.getInstance();

    /**
     * Gets selected category.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gettingMeals.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {

                ArrayList<Meal> randomMeals = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    Meal randomMeal = getRandomMeal();
                    randomMeals.add(randomMeal);
                    System.out.println(randomMeal.getStrMeal());
                }

                setMealStrings(randomMeals);
            }
        });

        new Thread(gettingMeals).start();
    }

    Task<Void> gettingMeals = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            meatMeals = getAllMeatMeals();
            veganMeals = getMealCategory("Vegan");
            vegetarianMeals = getMealCategory("Vegetarian");

            return null;
        }
    };

    public Meal findMeal(String mealName) {
        ArrayList<Meal> allMeals = new ArrayList();
        allMeals.addAll(veganMeals);
        allMeals.addAll(vegetarianMeals);
        allMeals.addAll(meatMeals);

        for (int i = 0; i < allMeals.size(); i++) {

            if (allMeals.get(i).getStrMeal().equals(mealName)) {

                return allMeals.get(i);
            }
        }

        return null;
    }

    public void searchMealConfirm() {

        currentUser.setPoints(currentUser.getPoints() + 25);
        mealBoxText.setText("You have earned 25 pts for finding a meal!");


        addGenericFeat(currentUser.getID(), currentUser.getPoints());
    }

    public void search() {

        String mealName = searchInput.getText();
        Meal foundMeal = findMeal(mealName);

        if (foundMeal != null) {

            searchStatus.setText(foundMeal.getStrMeal() + "has been selected!");
            searchMealName.setText(foundMeal.getStrMeal());
        } else {

            searchStatus.setText("This meal does not exist");
        }
    }

    /**
     * Sets meal strings.
     *
     * @param mealCategory the meal category
     */
    public void setMealStrings(ArrayList<Meal> mealCategory) {

        ObservableList<String> mealViewContents = mealView.getItems();
        mealViewContents.remove(0, mealViewContents.size());

        mealCategory.forEach(meal -> {

            mealViewContents.add(meal.getStrMeal());
        });
    }

    /**
     * Display veg meals.
     */
    public void displayVegMeals() {

        meatOpt.setSelected(false);
        veganOpt.setSelected(false);

        setMealStrings(vegetarianMeals);
    }

    /**
     * Display vegan meals.
     */
    public void displayVeganMeals() {

        meatOpt.setSelected(false);
        vegOpt.setSelected(false);

        setMealStrings(veganMeals);
    }

    /**
     * Display meat meals.
     */
    public void displayMeatMeals() {

        vegOpt.setSelected(false);
        veganOpt.setSelected(false);

        setMealStrings(meatMeals);
    }

    /**
     * Gets meal points.
     */
    public void getMealPoints() {

        if (localProduceBtn.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 15);

            mealBoxText.setText("You have earned 15 points for eating local produce");
            addGenericFeat(currentUser.getID(), 15);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        }

        if (veganOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 100);

            mealBoxText.setText("You have earned 100 pts for eating a vegan meal!");

            addGenericFeat(currentUser.getID(), 100);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        } else if (meatOpt.isSelected()) {

            mealBoxText.setText("You have earned 0 pts for eating a meal with meat!");
        } else if (vegOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 50);

            mealBoxText.setText("You have earned 50 pts for eating a vegetarian meal!");

            System.out.println(currentUser.toString());

            addGenericFeat(currentUser.getID(), 50);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        } else {

            currentUser.setPoints(currentUser.getPoints() + 25);

            mealBoxText.setText("You have selected a random meal, and have been awarded 25 points!");

            addGenericFeat(currentUser.getID(), 25);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        }
    }
}