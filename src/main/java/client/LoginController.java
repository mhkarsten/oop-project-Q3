package client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Label loginStatus;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    /**Login method.
     *
     * @param event Added an event parameter
     * @throws Exception Throws exception if the event is invalid
     */
    public void login(ActionEvent event) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/users")
        .header("accept", "application/json").basicAuth("user", "user").asJson();

        System.out.println(response.getBody().getArray().get(0));


        if (usernameField.getText().equals("user") && passwordField.getText().equals("pass")) {

            loginStatus.setText("Status: You have logged in!");

            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));

            Scene scene = new Scene(root, 600, 400);

            mainStage.setScene(scene);
            mainStage.show();

            Stage oldStage = (Stage) loginStatus.getScene().getWindow();
            oldStage.close();
        } else {
            loginStatus.setText("Status: Username or password is not correct.");
        }
    }

    public void register(ActionEvent event) throws Exception {
        System.out.println("Register");

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/register")
            .header("accept", "application/json")
            .field("username", usernameField.getText())
            .field("password", passwordField.getText())
            .asJson();
    }
}
