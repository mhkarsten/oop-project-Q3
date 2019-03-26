package client.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MyStage {

    private static Stage instance;

    public static class Screens {
        public final static String BASE_DIR = "src/main/resources/";
        public final static String LOGIN =  BASE_DIR + "loginScreen.fxml";
        public final static String ROOT =  BASE_DIR + "rootScreen.fxml";
    }

    public static Stage getInstance() {
        if(instance == null) {
            instance = new Stage();
        }

        return instance;
    }

    public static Stage getInstance(Stage stage) {
        if(instance == null) {
            instance = stage;
        }

        return instance;
    }
    public static void switchScene(String sceneName) throws IOException {
        URL url = new File(sceneName).toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 600,400);

        MyStage.getInstance().setScene(scene);
        MyStage.getInstance().show();
    }
}
