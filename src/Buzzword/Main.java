package Buzzword;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class Main extends Application {
    static UserData userData = new UserData("");

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginWindow login = LoginWindow.getLogin();
        login.init(primaryStage);
        CreateProfileWindow createProfileWindow = CreateProfileWindow.getWindow();
        createProfileWindow.init(primaryStage);
        AppMessageDialogSingleton appMessageDialogSingleton = AppMessageDialogSingleton.getSingleton();
        appMessageDialogSingleton.init(primaryStage);
        YesNoCancelDialogSingleton yesNoCancelDialogSingleton = YesNoCancelDialogSingleton.getSingleton();
        yesNoCancelDialogSingleton.init(primaryStage);

        Parent root = FXMLLoader.load(getClass().getResource("buzzwordhome.fxml"));
        primaryStage.setTitle("Buzzword");
        primaryStage.setScene(new Scene(root, 800, 690));
        primaryStage.show();
    }

    public void showLoginScreen(LoginWindow window) throws IOException {
        window.show("Login","Enter username and password");
        if (window.getSelection()=="Login" && window.getLoginField().getText()!=null){
            userData.setUsername(window.getLoginField().getText());
        }
    }
    public void showCreateProfileScreen(CreateProfileWindow window) throws IOException {
        window.show("Create new profile","Enter a username and password");
        handleNewProfile(window.getLoginField().getText(), window.getPasswordField().getText(), window.getConfirmField().getText());
    }

    private void handleNewProfile(String username, String password, String confirm){

    }

    public UserData getUserData(){ return userData; }

    public static void main(String[] args) {
        launch(args);
    }
}
