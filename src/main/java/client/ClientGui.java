package client;

import client.service.MyStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGui extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MyStage.switchScene(MyStage.Screens.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
