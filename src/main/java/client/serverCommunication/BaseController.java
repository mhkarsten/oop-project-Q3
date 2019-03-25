package client.serverCommunication;

import client.Service.UserSession;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

public class BaseController {

    protected RestTemplate restTemplate = new RestTemplate();

    public BaseController() {
        UserSession session = UserSession.getInstace();



        this.restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(
            session.getUserName(), session.getPassword()));

    }

//    public static void RedirectToLogin() throws IOException {
//        URL resourceUrl = BaseController.class.getResource("src/main/resources/loginScreen.fxml");
//        Parent root = FXMLLoader.load(resourceUrl);
//
//        Stage stage = new Stage();
//        Scene scene = new Scene(root, 600,400);
//
//        stage.setScene(scene);
//        stage.show();
//    }
}
