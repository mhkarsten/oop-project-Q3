package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientGui extends Application {


    @Override
    public void start(Stage stage) {

        try {
//            URL resourceUrl = getClass().getResource("src/main/resources/loginScreen.fxml");
            URL resourceUrl = getClass().getResource("/loginScreen.fxml");
            Parent root = FXMLLoader.load(resourceUrl);

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
