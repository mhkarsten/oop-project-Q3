package client.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


import static client.retrive.UserRetrieve.getUser;
import static client.retrive.UserRetrieve.updateUser;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;

public class RootController implements Initializable {

    @FXML
    public Button profile;
    public Button action;
    public Button stretchbutton;
    public Button score;
    public Button compare;
    public Button food;
    public Button transport;
    public Button energy;
    public SplitPane mainPane;
    public AnchorPane sidebarPane;
    public AnchorPane changePane;
    public LineChart<String,Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        action.setOnAction((ActionEvent evt) -> {
            try {
                openFoodScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        score.setOnAction((ActionEvent evt) -> {
            try {

                openScoreScreen();
            } catch (IOException e) {
                e.printStackTrace();;
            }
        });
    }

    public void openFoodScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/foodScreen.fxml"));
        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openScoreScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/scoreScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openProfileScreen() throws IOException {

        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/profileScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openCompareScreen() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/compareScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openMyScore() throws IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/scoreScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void openTransportScreen() throws  IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/transportScreen.fxml"));

        changePane = newScreen.load();
        mainPane.getItems().set(1, changePane);
    }

    public void openEnergyScreen() throws  IOException {
        FXMLLoader newScreen = new FXMLLoader(getClass().getResource("/energyScreen.fxml"));

        changePane = newScreen.load();

        mainPane.getItems().set(1, changePane);
    }

    public void moveButtons(){
        Timeline rowMove = new Timeline();

        Timeline fade = new Timeline();
        fade.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(1),
                new KeyValue(action.opacityProperty(), 0),
                new KeyValue(profile.opacityProperty(), 0),
                new KeyValue(score.opacityProperty(), 0),
                new KeyValue(compare.opacityProperty(), 0),
                new KeyValue(food.opacityProperty(), 0),
                new KeyValue(transport.opacityProperty(), 0),
                new KeyValue(energy.opacityProperty(), 0)
            )
        );
        rowMove.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(2),
                new KeyValue(stretchbutton.scaleXProperty(),200)
            )
        );

        fade.play();
        fade.setOnFinished(e -> rowMove.play());
        rowMove.setOnFinished(e -> fade.stop());
        rowMove.setOnFinished(e -> rowMove.stop());
    }

    public void moveBackButton(){
        Timeline rowMove = new Timeline();

        Timeline fade = new Timeline();
        fade.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(1),
                new KeyValue(action.opacityProperty(), 1),
                new KeyValue(profile.opacityProperty(), 1),
                new KeyValue(score.opacityProperty(), 1),
                new KeyValue(compare.opacityProperty(), 1),
                new KeyValue(food.opacityProperty(), 1),
                new KeyValue(transport.opacityProperty(), 1),
                new KeyValue(energy.opacityProperty(), 1)
            )
        );
        rowMove.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(2),
                new KeyValue(stretchbutton.scaleXProperty(),1)
            )
        );

        fade.play();
        fade.setOnFinished(e -> rowMove.play());
        rowMove.setOnFinished(e -> fade.stop());
        rowMove.setOnFinished(e -> rowMove.stop());
    }

    public void btn(ActionEvent event){
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add( new XYChart.Data<String, Number>("Jan", 200));
        lineChart.getData().add(series);
    }


    public static Label selectOption(MouseEvent event, Label choice) {

        if (choice != null) {

            choice.setBackground(new Background(new BackgroundFill(RED, null, null)));
        }

        Label chosenOption = (Label) event.getSource();

        chosenOption.setBackground(new Background(new BackgroundFill(WHITE, new CornerRadii(1), null)));
        return chosenOption;
    }

    public static void addPointsUser(int points) {
//      UserSession.getInstance().getUser.getId()
        User[] currentUser = getUser(1L);
        currentUser[0].setPoints(currentUser[0].getPoints() + points);

        updateUser(1L, currentUser[0].getName(), currentUser[0].getPoints());

        System.out.println(currentUser.toString());
    }
}
