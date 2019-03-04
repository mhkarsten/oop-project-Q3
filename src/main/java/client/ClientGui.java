package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ClientGui extends Application {


    @Override
<<<<<<< HEAD:src/main/java/client/ClientGUI.java
    public void start(Stage stage) throws IOException {

        URL url = new File("src/main/java/resources/client.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        String screenText = "Save the Enviroment!";

        Label test = new Label(screenText);

        Scene mainScene = new Scene(new StackPane(test), 640,480);
        stage.setScene(mainScene);
        stage.show();
=======
    public void start(Stage stage) {

        try {
            URL resourceUrl = getClass().getResource("src/main/resources/loginScreen.fxml");
            Parent root = FXMLLoader.load(resourceUrl);

            Scene scene = new Scene(root, 600, 400);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("There was an issue loading the fxml.");
            e.printStackTrace();
        }
>>>>>>> 4d16df88e15aeb88869fa0c5574e9d295d9c3cb8:src/main/java/client/ClientGui.java
    }

    public static void main(String[] args) {

        launch(args);
    }
}
