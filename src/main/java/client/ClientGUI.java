package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI extends Application {


    @Override
    public void start(Stage stage) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
        } catch (IOException e) {

            System.out.println("There was an issue loading the fxml.");
        }

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
