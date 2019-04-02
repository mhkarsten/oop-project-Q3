package client.UI;

import client.Service.MyRestTemplate;
import client.Service.MyStage;
import client.Service.UrlEndPoints;
import client.Service.UserSession;
import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import javax.servlet.http.Cookie;

public class LoginController {

    @FXML
    private Label loginStatus;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void onEnter(ActionEvent ae) throws IOException {
        login(ae);
    }

    /**
     * Login method.
     *
     * @throws Exception Throws exception if the event is invalid
     */
    public void login(ActionEvent event) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(usernameField.getText(), passwordField.getText()));

        try {

            HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);
            HttpEntity<User> entity = new HttpEntity<>(headers);

            ResponseEntity<User> response = restTemplate.exchange(UrlEndPoints.Auth.LOGIN + "/" + usernameField.getText(),
                HttpMethod.POST, entity, User.class);

            User user = response.getBody();

            UserSession.getInstace().setUserName(usernameField.getText());
            UserSession.getInstace().setPassword(passwordField.getText());
            UserSession.getInstace().setCurrentUser(user);

            System.out.println(UserSession.getInstace().getCurrentUser().toString());

            if (user != null) {
                loginStatus.setText("Status: You have logged in!");
                MyStage.switchScene(MyStage.Screens.ROOT);
            }

            loginStatus.setText("Status: Username or password is not correct.");

        } catch (Exception e) {
            loginStatus.setText("Status: Username or password is not correct.");
            System.out.println(e);
        }

    }

    /**Method to post a new user (CREATE).
     *
     */
    public void register(ActionEvent event) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = MyRestTemplate.getBaseHeaders(MediaType.APPLICATION_XML);

        try {

            User newUser = new User(usernameField.getText(), passwordField.getText());
            HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
            User user = restTemplate.postForObject(UrlEndPoints.Auth.REGISTER, requestBody, User.class);

            UserSession.getInstace().setCurrentUser(user);

            if (user != null && user.getID() != 0) {
                loginStatus.setText("(Client Side) New user created" + user.getName());

                UserSession.getInstace().setUserName(usernameField.getText());
                UserSession.getInstace().setPassword(passwordField.getText());
                MyStage.switchScene(MyStage.Screens.ROOT);
            }
        } catch (Exception e) {
            loginStatus.setText("User already exists.");
            System.out.println(e);
        }
    }
}
