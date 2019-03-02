package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI extends Application {


    @Override
    public void start(Stage stage) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/loginScreen.fxml"));

            Scene scene = new Scene(root, 600, 400);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("There was an issue loading the fxml.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
