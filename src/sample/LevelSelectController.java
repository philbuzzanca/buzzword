package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class LevelSelectController {
    @FXML
        private Button usernameButton;
    @FXML
        private Button homeButton;
    @FXML
        private Label modeTitle;
    @FXML
        private Circle circleOne;
    @FXML
        private Circle circleTwo;
    @FXML
        private Circle circleThree;
    @FXML
        private Circle circleFour;



    public void updateLevelData(String name, String title){
        usernameButton.setText(name);
        modeTitle.setText(title);
    }

    public void homeButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordhome.fxml"));
        Stage stage = (Stage) usernameButton.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        Controller cont = loader.<Controller>getController();
        stage.show();
    }

    public void circleOnePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        GameplayController cont = loader.<GameplayController>getController();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), "Level 1","Target: 50 points");
        stage.show();
    }

    public void circleTwoPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        GameplayController cont = loader.<GameplayController>getController();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), "Level 2","Target: 70 points");
        stage.show();
    }

    public void circleThreePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        GameplayController cont = loader.<GameplayController>getController();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), "Level 3", "Target: 100 points");
        stage.show();
    }

    public void circleFourPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        GameplayController cont = loader.<GameplayController>getController();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), "Level 4","Target: 150 points");
        stage.show();
    }

}
