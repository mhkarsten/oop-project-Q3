package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ClientGUI extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        URL url = new File("src/main/java/resources/client.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        String screenText = "Save the Enviroment!";

        Label test = new Label("Hello, JavaFX " + screenText);

        Scene mainScene = new Scene(new StackPane(test), 640,480);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
