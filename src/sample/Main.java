package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static GameData gamedata;

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginWindow login = LoginWindow.getLogin();
        login.init(primaryStage);
        CreateProfileWindow createProfileWindow = CreateProfileWindow.getWindow();
        createProfileWindow.init(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("buzzwordhome.fxml"));
        primaryStage.setTitle("Buzzword");
        primaryStage.setScene(new Scene(root, 800, 690));
        primaryStage.show();
    }

    public void showLoginScreen(LoginWindow window) throws IOException {
        window.show("Login","Enter username and password");
        if (window.getSelection()=="Login" && window.getLoginField().getText()!=null){
            gamedata.setUsername(window.getLoginField().getText());
        }
    }
    public void showCreateProfileScreen(CreateProfileWindow window) throws IOException {
        window.show("Create new profile","Enter a username and password");
    }

    public GameData getGamedata(){
        return gamedata;
    }




    public static void main(String[] args) {
        gamedata = new GameData();
        launch(args);
    }
}
