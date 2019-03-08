package client.FXMLControllers;

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

        if (usernameField.getText().equals("user") && passwordField.getText().equals("pass")) {

            loginStatus.setText("Status: You have logged in!");

            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/rootScreen.fxml"));

            Scene scene = new Scene(root, 600, 400);

            mainStage.setScene(scene);
            mainStage.show();

            Stage oldStage = (Stage) loginStatus.getScene().getWindow();
            oldStage.close();
        } else {

            loginStatus.setText("Status: Username or password is not correct.");
        }
    }
}
