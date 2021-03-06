package server.model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * The type Meal.
 */
public class Meal {

    private String strMeal;
    private String idMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;

    private ArrayList<String> strTags;
    private ArrayList<String> strIngredients;
    private ArrayList<String> strMeasures;

    private String strSource;
    private String strMealThumb;
    private String strYoutube;

    /**
     * Instantiates a new Meal.
     */
    public Meal() {
    }

    /**
     * Instantiates a new Meal.
     *
     * @param strMeal         the str meal
     * @param idMeal          the id meal
     * @param strCategory     the str category
     * @param strArea         the str area
     * @param strTags         the str tags
     * @param strIngredients  the str ingredients
     * @param strMeasures     the str measures
     * @param strSource       the str source
     * @param strMealThumb    the str meal thumb
     * @param strYoutube      the str youtube
     * @param strInstructions the str instructions
     */
    //There are this many arguments/fields to be a one-to-one mapping of the meal JSON objects returned by the MealDB
    public Meal(String strMeal, String idMeal, String strCategory, String strArea, ArrayList<String>
        strTags, ArrayList<String> strIngredients, ArrayList<String> strMeasures, String strSource,
                String strMealThumb, String strYoutube, String strInstructions) {
        this.strMeal = strMeal;
        this.idMeal = idMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strTags = strTags;
        this.strIngredients = strIngredients;
        this.strMeasures = strMeasures;
        this.strSource = strSource;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strInstructions = strInstructions;
    }

    /**
     * Json to meal meal [ ].
     *
     * @param jsonMeal the json meal
     * @return the meal [ ]
     */
    public static Meal[] jsonToMeal(JSONObject jsonMeal) {

        Object mealList = jsonMeal.get("meals");
        ArrayList<LinkedHashMap> meal = (ArrayList<LinkedHashMap>) mealList;
        LinkedHashMap linkedMeal = meal.get(0);

        Meal newMeal = new Meal();

        newMeal.setStrMeal((String) linkedMeal.get("strMeal"));
        newMeal.setIdMeal((String) linkedMeal.get("idMeal"));
        newMeal.setStrArea((String) linkedMeal.get("strArea"));
        newMeal.setStrCategory((String) linkedMeal.get("strCategory"));
        newMeal.setStrMealThumb((String) linkedMeal.get("strMealThumb"));
        newMeal.setStrSource((String) linkedMeal.get("strSource"));
        newMeal.setStrYoutube((String) linkedMeal.get("strYoutube"));
        newMeal.setStrInstructions((String) linkedMeal.get("strInstructions"));

        String tempTags = (String) linkedMeal.get("strTags");

        if (tempTags != null) {

            String[] tempArray = tempTags.split(",");

            ArrayList<String> tempList = new ArrayList<>();
            tempList.addAll(Arrays.asList(tempArray));

            newMeal.setStrTags(tempList);
        }

        for (int i = 1; i < 15; i++) {

            ArrayList<String> tempIngredients = new ArrayList<>();

            tempIngredients.add((String) linkedMeal.get("strIngredient" + i));

            newMeal.setStrIngredients(tempIngredients);
        }

        for (int i = 1; i < 15; i++) {

            ArrayList<String> tempMeasure = new ArrayList<>();

            tempMeasure.add((String) linkedMeal.get("strMeasure" + i));

            newMeal.setStrMeasures(tempMeasure);
        }

        Meal[] encasedMeal = new Meal[1];
        encasedMeal[0] = newMeal;

        return encasedMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public ArrayList<String> getStrTags() {
        return strTags;
    }

    public void setStrTags(ArrayList<String> strTags) {
        this.strTags = strTags;
    }

    public ArrayList<String> getStrIngredients() {
        return strIngredients;
    }

    public void setStrIngredients(ArrayList<String> strIngredients) {
        this.strIngredients = strIngredients;
    }

    public ArrayList<String> getStrMeasures() {
        return strMeasures;
    }

    public void setStrMeasures(ArrayList<String> strMeasures) {
        this.strMeasures = strMeasures;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }
}
