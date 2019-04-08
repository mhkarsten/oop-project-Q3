package client.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Manages the stage/windown by providing a singleton class.
 */
public class MyStage {

    private static Stage instance;

    public static class Screens {
        public static final String BASE_DIR = "src/main/resources/";
        public static final String LOGIN =  BASE_DIR + "loginScreen.fxml";
        public static final String ROOT =  BASE_DIR + "rootScreen.fxml";
    }



    /**
     * Checks if the instance is already instantiated, and if so return the single instance.
     * If not creates the single instance and returns it.
     * @return the singleton instance of the stage
     */
    public static Stage getInstance() {
        if (instance == null) {
            instance = new Stage();
        }

        return instance;
    }

    /**
     * Constructor for providing the initial stage.
     * Checks if the instance is already instantiated, and if so return the single instance.
     * If not creates the single instance and returns it.
     * @return the singleton instance of the stage
     */
    public static Stage getInstance(Stage stage) {
        if (instance == null) {
            instance = stage;
        }

        return instance;
    }

    /**
     * Creates a new scene based on the provides screen name (managed in MyStage.Screens) and sets the singleton stage's screen.
     * @param sceneName name of the new scene.
     * @throws IOException throws IOException if it cannot load the new scene
     */
    public static void switchScene(String sceneName) throws IOException {
        URL url = new File(sceneName).toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 600,400);

        MyStage.getInstance().setScene(scene);
        MyStage.getInstance().show();
    }

    public static void clearInstance() {
        instance = null;
    }
}
