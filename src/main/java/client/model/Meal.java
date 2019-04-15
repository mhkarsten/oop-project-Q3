package client.model;

import java.util.ArrayList;

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

    public Meal() {
    }

    /**
     * Constructor for a meal with parameters.
     * @param strMeal name of the meal
     * @param idMeal id of the meal
     * @param strCategory the category name
     * @param strArea the area name
     * @param strTags the tags associated with the meal
     * @param strIngredients the ingredients of the meal
     * @param strMeasures the measurements of the ingredients
     * @param strSource the name of the source
     * @param strMealThumb the meal thumb information
     * @param strYoutube a youtube tutorial link to make the meal
     * @param strInstructions instructions to make the meal
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
