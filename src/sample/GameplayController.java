package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class GameplayController {
    @FXML
        private Button usernameButton;
    @FXML
        private Label levelNumber;
    @FXML
        private Button homeButton;
    @FXML
        private Label modeTitle;
    @FXML
        private Label targetLabel;


    public void homeButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordhome.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        Controller cont = loader.<Controller>getController();
        stage.show();
    }

    public void updateData(String name, String title, String level, String target) {
        usernameButton.setText(name);
        modeTitle.setText(title);
        levelNumber.setText(level);
        targetLabel.setText(target);
    }
}
