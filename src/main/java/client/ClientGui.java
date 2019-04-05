package client;

import client.Service.MyStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGui extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MyStage.switchScene(MyStage.Screens.LOGIN);
        //        URL url = new File("src/main/resources/foodScreen.fxml").toURL();
        //        Parent root = FXMLLoader.load(url);
        //        Scene scene = new Scene(root, 400, 400);
        //        stage.setScene(scene);
        //        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
