package Buzzword.Controller;

import Buzzword.View.AppMessageDialogSingleton;
import Buzzword.View.CreateProfileWindow;
import Buzzword.View.LoginWindow;
import Buzzword.View.YesNoCancelDialogSingleton;
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

public class HomeController {
    public Button getLoginButton() {
        return loginButton;
    }
    private static boolean loggedIn = false;

    @FXML
            private Button loginButton;

    Button getCnpButton() {
        return cnpButton;
    }

    @FXML
            private Button cnpButton;

    Button getStartButton() {
        return startButton;
    }

    @FXML
            private Button startButton;

    MenuButton getSelectModeMenu() {
        return selectModeMenu;
    }

    @FXML
            private MenuButton selectModeMenu;

    private static Main game = new Main();
    private String gamemode = "Dictionary Words";
    private Set<String> boggleDictionary = BoggleSolver.loadHash("words/boggleDictionary.txt");
    private Set<String> namesDictionary = BoggleSolver.loadHash("words/firstNames.txt");
    private Set<String> threeLetterDictionary = BoggleSolver.loadHash("words/threeLetterWords.txt");
    private Set<String> dictionary = boggleDictionary;

    @FXML
    public void handleHelpButton(){
        AppMessageDialogSingleton s = AppMessageDialogSingleton.getSingleton();
        s.show("Help","Buzzword is a challenging vocabulary game.\nEarn points by finding words in the grid.\nTiles can only be selected once per word.\nHave fun!");
    }


    public void loginButtonClicked() throws IOException {
        if(!loggedIn) {
            LoginWindow.getLogin().getLoginField().clear();
            LoginWindow.getLogin().getPasswordField().clear();
            try {
                game.showLoginScreen(LoginWindow.getLogin());
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoginWindow loginWindow = LoginWindow.getLogin();
            if (loginWindow.getSelection().equals("Login")) {
                loginButton.setMinWidth(cnpButton.getWidth());
                loginButton.setMaxWidth(cnpButton.getWidth());
                loginButton.setText(loginWindow.getLoginField().getText());
                cnpButton.setVisible(false);
                selectModeMenu.setDisable(false);
                startButton.setDisable(false);
                this.loggedIn = true;
            }
        }
        else {
            YesNoCancelDialogSingleton s = YesNoCancelDialogSingleton.getSingleton();
            s.show("Log out", "Are you sure you want to log out?");
            if(s.getSelection().equals("Yes")){
                cnpButton.setVisible(true);
                startButton.setDisable(true);
                this.loggedIn = false;
                selectModeMenu.setDisable(true);
                this.loginButton.setText("Login");
            }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordlevelselect.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        LevelSelectController cont = loader.getController();
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
        this.gamemode="Three Letter Words";
        this.dictionary = threeLetterDictionary;
    }
}