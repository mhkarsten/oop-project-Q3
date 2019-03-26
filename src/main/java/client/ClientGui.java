package client;

import client.Service.MyStage;
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
    public void start(Stage stage) throws IOException { MyStage.switchScene(MyStage.Screens.LOGIN); }

    public static void main(String[] args) {
        launch(args);
    }
}
