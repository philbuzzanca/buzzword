package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */

public class Controller {
    public Button getLoginButton() {
        return loginButton;
    }


    @FXML
            private Button loginButton;
    @FXML
        private MenuItem gm1;
    @FXML
        private MenuItem gm2;
    @FXML
        private MenuItem gm3;

    public Button getCnpButton() {
        return cnpButton;
    }

    @FXML
            private Button cnpButton;
    @FXML
            private Label modeTitle;

    public Button getStartButton() {
        return startButton;
    }

    @FXML
            private Button startButton;

    public MenuButton getSelectModeMenu() {
        return selectModeMenu;
    }

    @FXML
            private MenuButton selectModeMenu;

    static Main game = new Main();
    String gamemode = new String("Dictionary Words");
    Set<String> boggleDictionary = BoggleSolver.loadHash("C:/Users/Phil/IdeaProjects/buzzwordfx/words/boggleDictionary.txt");
    Set<String> namesDictionary = BoggleSolver.loadHash("C:/Users/Phil/IdeaProjects/buzzwordfx/words/firstNames.txt");
    Set<String> animalsDictionary = BoggleSolver.loadHash("C:/Users/Phil/IdeaProjects/buzzwordfx/words/animals.txt");
    Set<String> dictionary = boggleDictionary;


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
    }

    public void startButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordlevelselect.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        LevelSelectController cont = loader.<LevelSelectController>getController();
        cont.updateLevelData(game.getUserData().getUsername(),this.gamemode, this.dictionary);
        stage.show();
    }

    public void updateGameMode1(){
        this.gamemode="Dictionary Words";
        this.dictionary = boggleDictionary;
    }
    public void updateGameMode2(){
        this.gamemode="Common Names";
        this.dictionary = namesDictionary;
    }
    public void updateGameMode3(){
        this.gamemode="Animals";
        this.dictionary = animalsDictionary;
    }
}