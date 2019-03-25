package client.FXMLControllers;



import client.Service.UserSession;
import client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;


import java.io.IOException;

public class LoginController {

    @FXML
    private Label loginStatus;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private RestTemplate restTemplate = new RestTemplate();


    @FXML
    public void onEnter(ActionEvent ae) throws IOException {
        login(ae);
    }

    /**Login method.
     *
     * @throws Exception Throws exception if the event is invalid
     */
    public void login(ActionEvent ae) throws IOException {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(usernameField.getText(), passwordField.getText()));

        try {
            String fooResourceUrl = "http://localhost:8080/";
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);

            HttpHeaders headers = response.getHeaders();
            UserSession.getInstace().setUserName(usernameField.getText());
            UserSession.getInstace().setPassword(passwordField.getText());

            loginStatus.setText("Status: You have logged in!");

            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/rootScreen.fxml"));

            Scene scene = new Scene(root, 600, 400);

            mainStage.setScene(scene);
            mainStage.show();

            Stage oldStage = (Stage) loginStatus.getScene().getWindow();
            oldStage.close();
        } catch (Exception e) {
            loginStatus.setText("Status: Username or password is not correct.");
            System.out.println(e);
        }

    }

    /**Method to post a new user (CREATE).
     *
     * }
     */
    public void register(ActionEvent event) {

        User newUser = new User(usernameField.getText(), passwordField.getText(), 0);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);

        User user = restTemplate.postForObject("http://localhost:8080/register", requestBody, User.class);

        if (user != null && user.getID() != 0) {

            loginStatus.setText("(Client Side) New user created" + user.getName());
        } else {

            loginStatus.setText("User already exists.");
        }
    }
}
