package client.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    private String strMeal = "meal";
    private String strMealTest = "mealTest";

    private String idMeal = "12";
    private String idMealTest = "14";

    private String strCategory = "category";
    private String strCategoryTest = "categoryTest";

    private String strArea = "area";
    private String strAreaTest = "areaTest";

    private String strInstructions = "instructions";
    private String strInstructionsTest = "instructionsTest";

    private ArrayList<String> strTags = new ArrayList<>(Arrays.asList("tag", "tag"));
    private ArrayList<String> strTagsTest  = new ArrayList<>(Arrays.asList("anotherTag", "tag"));;

    private ArrayList<String> strIngredients  = new ArrayList<>(Arrays.asList("cheese", "tomato"));;
    private ArrayList<String> strIngredientsTest  = new ArrayList<>(Arrays.asList("testIngr", "beef"));;

    private ArrayList<String> strMeasures = new ArrayList<>(Arrays.asList("12", "18"));;;
    private ArrayList<String> strMeasuresTest = new ArrayList<>(Arrays.asList("24", "34"));;;

    private String strSource = "source";
    private String strSourceTest = "sourceTest";

    private String strMealThumb = "thumb";
    private String strMealThumbTest = "thumbTest";

    private String strYoutube = "youtube";
    private String strYoutubeTest = "youtubeTest";

    private Meal meal;

    @BeforeEach
    void setUp() {
        this.meal = new Meal(strMeal, idMeal,strCategory, strArea, strTags, strIngredients, strMeasures, strSource, strMealThumb, strYoutube, strInstructions);
    }

    @AfterEach
    void tearDown() {
        this.meal = null;
    }

    @Test
    void emptyConstructor() {
        Meal meal = new Meal();
        assertNotNull(meal);
    }



    @Test
    void getStrMeal() {
        assertEquals(strMeal, this.meal.getStrMeal());
        assertNotEquals(strMealTest, this.meal.getStrMeal());
    }

    @Test
    void setStrMeal() {
        assertEquals(strMeal, this.meal.getStrMeal());

        this.meal.setStrMeal(strMealTest);
        assertEquals(strMealTest, this.meal.getStrMeal());
    }

    @Test
    void getIdMeal() {
        assertEquals(idMeal, this.meal.getIdMeal());
        assertNotEquals(idMealTest, this.meal.getIdMeal());
    }

    @Test
    void setIdMeal() {
        assertEquals(idMeal, this.meal.getIdMeal());

        this.meal.setIdMeal(idMealTest);
        assertEquals(idMealTest, this.meal.getIdMeal());
    }

    @Test
    void getStrCategory() {
        assertEquals(strCategory, this.meal.getStrCategory());
        assertNotEquals(strCategoryTest, this.meal.getStrCategory());
    }

    @Test
    void setStrCategory() {
        assertEquals(strCategory, this.meal.getStrCategory());

        this.meal.setStrCategory(strCategoryTest);
        assertEquals(strCategoryTest, this.meal.getStrCategory());
    }

    @Test
    void getStrArea() {
        assertEquals(strArea, this.meal.getStrArea());
        assertNotEquals(strAreaTest, this.meal.getStrArea());
    }

    @Test
    void setStrArea() {
        assertEquals(strArea, this.meal.getStrArea());

        this.meal.setStrArea(strAreaTest);
        assertEquals(strAreaTest, this.meal.getStrArea());

    }

    @Test
    void getStrTags() {
        assertEquals(strTags, this.meal.getStrTags());
        assertNotEquals(strTagsTest, this.meal.getStrTags());
    }

    @Test
    void setStrTags() {
        assertEquals(strTags, this.meal.getStrTags());

        this.meal.setStrTags(strTagsTest);
        assertEquals(strTagsTest, this.meal.getStrTags());

    }

    @Test
    void getStrIngredients() {
        assertEquals(strIngredients, this.meal.getStrIngredients());
        assertNotEquals(strIngredientsTest, this.meal.getStrIngredients());
    }

    @Test
    void setStrIngredients() {
        assertEquals(strIngredients, this.meal.getStrIngredients());
        this.meal.setStrIngredients(strIngredientsTest);

        assertEquals(strIngredientsTest, this.meal.getStrIngredients());
    }

    @Test
    void getStrMeasures() {
        assertEquals(strMeasures, this.meal.getStrMeasures());
        assertNotEquals(strMeasuresTest, this.meal.getStrMeasures());
    }

    @Test
    void setStrMeasures() {
        assertEquals(strMeasures, this.meal.getStrMeasures());
        this.meal.setStrMeasures(strMeasuresTest);

        assertEquals(strMeasuresTest, this.meal.getStrMeasures());
    }

    @Test
    void getStrSource() {
        assertEquals(strSource, this.meal.getStrSource());
        assertNotEquals(strSourceTest, this.meal.getStrSource());
    }

    @Test
    void setStrSource() {
        assertEquals(strSource, this.meal.getStrSource());

        this.meal.setStrSource(strSourceTest);

        assertEquals(strSourceTest, this.meal.getStrSource());

    }

    @Test
    void getStrMealThumb() {
        assertEquals(strMealThumb, this.meal.getStrMealThumb());
        assertNotEquals(strMealThumbTest, this.meal.getStrMealThumb());
    }

    @Test
    void setStrMealThumb() {
        assertEquals(strMealThumb, this.meal.getStrMealThumb());
        this.meal.setStrMealThumb(strMealThumbTest);

        assertEquals(strMealThumbTest, this.meal.getStrMealThumb());
    }

    @Test
    void getStrYoutube() {
        assertEquals(strYoutube, this.meal.getStrYoutube());
        assertNotEquals(strYoutubeTest, this.meal.getStrYoutube());


    }

    @Test
    void setStrYoutube() {
        assertEquals(strYoutube, this.meal.getStrYoutube());
        this.meal.setStrYoutube(strYoutubeTest);

        assertEquals(strYoutubeTest, this.meal.getStrYoutube());
    }

    @Test
    void getStrInstructions() {
        assertEquals(strInstructions, this.meal.getStrInstructions());
        assertNotEquals(strInstructionsTest, this.meal.getStrInstructions());
    }

    @Test
    void setStrInstructions() {
        assertEquals(strInstructions, this.meal.getStrInstructions());

        this.meal.setStrInstructions(strInstructionsTest);
        assertEquals(strInstructionsTest, this.meal.getStrInstructions());
    }
}