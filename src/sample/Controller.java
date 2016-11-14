package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    static GameData gamedata;
    @FXML
            private Button loginButton;
    @FXML
            private Button cnpButton;
    @FXML
            private Label modeTitle;
    @FXML
            private Button startButton;
    @FXML
            private MenuButton selectModeMenu;

    static Main game = new Main();


    public void loginButtonClicked() throws IOException {
        LoginWindow.getLogin().getLoginField().clear();
        LoginWindow.getLogin().getPasswordField().clear();
        try {
            game.showLoginScreen(LoginWindow.getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginWindow loginWindow = LoginWindow.getLogin();
        if (loginWindow.getSelection().equals("Login")){
            loginButton.setMinWidth(cnpButton.getWidth());
            loginButton.setMaxWidth(cnpButton.getWidth());
            loginButton.setText(loginWindow.getLoginField().getText());
            cnpButton.setVisible(false);
            selectModeMenu.setDisable(false);
            startButton.setDisable(false);
        }
    }

    public void cnpButtonClicked() throws IOException {
        CreateProfileWindow.getWindow().getLoginField().clear();
        CreateProfileWindow.getWindow().getPasswordField().clear();
        try {
            game.showCreateProfileScreen(CreateProfileWindow.getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginWindow loginWindow = LoginWindow.getLogin();
    }

    public void startButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordlevelselect.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        LevelSelectController cont = loader.<LevelSelectController>getController();
        cont.updateUserButton(game.getGamedata().getUsername());
        stage.show();

    }
}
