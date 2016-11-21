package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class LoginWindow extends Stage {

    static LoginWindow window;
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Label usernameLabel;
    Label passwordLabel;
    Button loginButton;
    Button cancelButton;
    String username;
    String selection;

    public TextField getLoginField() {
        return loginField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    TextField loginField;
    PasswordField passwordField;

    private LoginWindow() {}

    public void setMessageLabel(String messageLabelText){
        messageLabel.setText(messageLabelText);
    }

    public static LoginWindow getLogin() {
        if (window==null) window = new LoginWindow();
        return window;
    }

    public void init(Stage primaryStage){
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        messageLabel = new Label();
        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");

        EventHandler<ActionEvent> loginHandler = event -> {
            LoginWindow.this.selection = ((Button) event.getSource()).getText();
            LoginWindow.this.hide();
        };

        loginButton.setOnAction(loginHandler);
        cancelButton.setOnAction(loginHandler);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(loginButton);
        buttonBox.getChildren().add(cancelButton);

        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        HBox usernameBox = new HBox();

        usernameLabel = new Label("Username: ");
        loginField = new TextField();
        usernameBox.getChildren().addAll(usernameLabel,loginField);
        messagePane.getChildren().add(usernameBox);

        HBox passBox = new HBox();
        passwordField = new PasswordField();
        passwordLabel = new Label("Password: ");
        passBox.getChildren().addAll(passwordLabel,passwordField);
        messagePane.getChildren().add(passBox);

        messagePane.getChildren().add(buttonBox);

        messagePane.setPadding(new Insets(10,20,20,20));
        messagePane.setSpacing(10);
        messageScene = new Scene(messagePane);
        this.setScene(messageScene);
    }


    public String getSelection(){ return selection; }
    public String getUsername() { return username; }

    public void show(String title, String message) {
        setTitle(title);
        setMessageLabel(message);
        showAndWait();
    }
}
