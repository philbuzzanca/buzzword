package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginWindow login = LoginWindow.getLogin();
        login.init(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("buzzwordhome.fxml"));
        primaryStage.setTitle("Buzzword");
        primaryStage.setScene(new Scene(root, 800, 690));
        primaryStage.show();
    }

    public static void showLoginScreen(LoginWindow window){
        window.show("Login","login");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
