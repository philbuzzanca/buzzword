package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Phil on 11/14/2016.
 */
public class LevelSelectController {
    @FXML
        private Button usernameButton;

    public void updateUserButton(String name){
        usernameButton.setText(name);
    }
}
