package client.ui;

import static client.service.AchievementGenerator.achNotification;
import static client.service.AchievementGenerator.giveUserAch;

import client.model.Achievement;
import client.model.Meal;
import client.model.User;
import client.retrieve.FoodRetrieve;
import client.retrieve.UserRetrieve;
import client.service.MyStage;
import client.service.UserSession;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Food controller.
 */
public class FoodController implements Initializable {

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

    private Label mealChoice;

    private User currentUser = UserSession.getInstance().getCurrentUser();
    private Stage currentStage = MyStage.getInstance();

    private UserRetrieve userRetrieve;
    private FoodRetrieve foodRetrieve;

    /**
     * Gets selected category.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Tooltip tooltipvegan = new Tooltip();
        tooltipvegan.setText("Being vegan is the single best way to reduce environmental impact! Read more on https://www.independent.co.uk/life-style/health-and-families/veganism-environmental-impact-planet-reduced-plant-based-diet-humans-study-a8378631.html");
        veganOpt.setTooltip(tooltipvegan);


        final Tooltip tooltipvegetarian = new Tooltip();
        tooltipvegetarian.setText("Read more about how this has a positive effect on the environment here! https://vegnews.com/2017/7/the-environmental-impacts-of-going-vegetarian-for-just-one-day");
        vegOpt.setTooltip(tooltipvegetarian);

        final Tooltip tooltipmeat = new Tooltip();
        tooltipmeat.setText("Read about how meat may affect the environment in a negative way here: https://www.peta.org/about-peta/faq/how-does-eating-meat-harm-the-environment/");
        meatOpt.setTooltip(tooltipvegetarian);

        final Tooltip tooltipproduce = new Tooltip();
        tooltipproduce.setText("There are lots of benefits of buying local produce. Read them here! https://arrowquip.com/blog/animal-science/top-benefits-buying-locally-grown-food");
        localProduceBtn.setTooltip(tooltipvegetarian);






        this.userRetrieve = new UserRetrieve();
        this.foodRetrieve = new FoodRetrieve();


        Task<Void> gettingMeals = new FetchMealsTask<Void>(new FoodRetrieve()) {

            @Override
            protected Void call() throws Exception {

                meatMeals = this.foodRetrieve.getAllMeatMeals();
                veganMeals = this.foodRetrieve.getMealCategory("Vegan");
                vegetarianMeals = this.foodRetrieve.getMealCategory("Vegetarian");

                return null;
            }
        };
        gettingMeals.stateProperty().addListener((observable, oldState, newState) -> {

            if (newState == Worker.State.SUCCEEDED) {

                ArrayList<Meal> randomMeals = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    Meal randomMeal = this.foodRetrieve.getRandomMeal();
                    randomMeals.add(randomMeal);
                    System.out.println(randomMeal.getStrMeal());
                }

                setMealStrings(randomMeals);
            }
        });

        new Thread(gettingMeals).start();
    }

    public abstract class FetchMealsTask<V> extends Task<V> {
        protected FoodRetrieve foodRetrieve;

        public FetchMealsTask(FoodRetrieve foodRetrieve) {
            this.foodRetrieve = foodRetrieve;
        }
    }

    /**
     * Method to find a meal based on the name of the meal.
     * @param mealName name of the meal
     * @return Returns the meal if it was found
     */
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

    /**
     * Method to handle successfully finding a meal.
     */
    public void searchMealConfirm() {

        currentUser.setPoints(currentUser.getPoints() + 25);
        mealBoxText.setText("You have earned 25 pts for finding a meal!");


        this.userRetrieve.addGenericFeat(currentUser.getID(), currentUser.getPoints());
    }

    /**
     * Method to handle choosing to find a meal.
     */
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
            this.userRetrieve.addGenericFeat(currentUser.getID(), 15);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        }

        if (veganOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 100);

            mealBoxText.setText("You have earned 100 pts for eating a vegan meal!");

            this.userRetrieve.addGenericFeat(currentUser.getID(), 100);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        } else if (meatOpt.isSelected()) {

            mealBoxText.setText("You have earned 0 pts for eating a meal with meat!");
        } else if (vegOpt.isSelected()) {

            currentUser.setPoints(currentUser.getPoints() + 50);

            mealBoxText.setText("You have earned 50 pts for eating a vegetarian meal!");

            System.out.println(currentUser.toString());

            this.userRetrieve.addGenericFeat(currentUser.getID(), 50);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        } else {

            currentUser.setPoints(currentUser.getPoints() + 25);

            mealBoxText.setText("You have selected a random meal, and have been awarded 25 points!");

            this.userRetrieve.addGenericFeat(currentUser.getID(), 25);
            Achievement newAch = giveUserAch(currentUser);
            achNotification(newAch, currentStage);
        }
    }
}