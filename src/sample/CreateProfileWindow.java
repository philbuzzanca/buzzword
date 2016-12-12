package sample;

import com.fasterxml.jackson.core.JsonFactory;
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
import com.fasterxml.jackson.core.*;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class CreateProfileWindow extends Stage {

    private static final char[] password = "jiejmn4fjJ4589j".toCharArray();
    private static final byte[] SALT = {
            (byte) 0xed, (byte) 0x55, (byte) 0x4a, (byte) 0x12,
            (byte) 0xed, (byte) 0x55, (byte) 0x4a, (byte) 0x12,
    };

    private static String encrypt(String property) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5andDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }

    private static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    static CreateProfileWindow window;
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Label usernameLabel;
    Label passwordLabel;
    Label confirmPasswordLabel;
    Button createButton;
    Button cancelButton;

    public TextField getLoginField() {
        return loginField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getConfirmField() {
        return passwordField;
    }

    TextField loginField;
    PasswordField passwordField;

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    PasswordField confirmPasswordField;

    private CreateProfileWindow() {}

    public void setMessageLabel(String messageLabelText){
        messageLabel.setText(messageLabelText);
    }

    public static CreateProfileWindow getWindow() {
        if (window==null) window = new CreateProfileWindow();
        return window;
    }

    public void init(Stage primaryStage){
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        messageLabel = new Label();
        createButton = new Button("Create");
        cancelButton = new Button("Cancel");

        EventHandler<ActionEvent> hideHandler = event -> {
            CreateProfileWindow.this.hide();
        };

        EventHandler<ActionEvent> newProfileHandler = event -> {
            String username = loginField.getText();
            String fileToSave = new String("users/"+username);
            File file = new File(fileToSave);
            if (file.exists()){
                new CreateProfileException("Username already in use");
                System.out.println("already in use");
            }
            else if (!passwordField.getText().equals(confirmPasswordField.getText())){
                new CreateProfileException("Passwords don't match");
                System.out.println("passwords don't match");
            }
            else if (passwordField.getText() == null || username == null || confirmPasswordField.getText() == null){
                new CreateProfileException("A field is blank");
                System.out.println("blank fields");
            }
            else {
                JsonFactory jsonFactory = new JsonFactory();
                try (OutputStream out = new FileOutputStream(file)) {
                    JsonGenerator generator = jsonFactory.createGenerator(out);
                    generator.writeStartObject();
                    generator.writeStringField("Username", username);
                    generator.writeStringField("Encrypted password", encrypt(passwordField.getText()));
                    generator.writeNumberField("Dictionary words",1);
                    generator.writeNumberField("Three Letter Words",1);
                    generator.writeNumberField("Common Names",1);
                    generator.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
                CreateProfileWindow.this.hide();
            }
        };

        createButton.setOnAction(newProfileHandler);
        cancelButton.setOnAction(hideHandler);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(createButton);
        buttonBox.getChildren().add(cancelButton);

        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        HBox usernameBox = new HBox();

        usernameLabel = new Label("Username:               ");
        loginField = new TextField();
        usernameBox.getChildren().addAll(usernameLabel,loginField);
        messagePane.getChildren().add(usernameBox);

        HBox passBox = new HBox();
        passwordField = new PasswordField();
        passwordLabel = new Label("Password:               ");
        passBox.getChildren().addAll(passwordLabel,passwordField);
        messagePane.getChildren().add(passBox);

        HBox confirmBox = new HBox();
        confirmPasswordField = new PasswordField();
        confirmPasswordLabel = new Label("Confirm Password: ");
        confirmBox.getChildren().addAll(confirmPasswordLabel,confirmPasswordField);
        messagePane.getChildren().add(confirmBox);

        messagePane.getChildren().add(buttonBox);

        messagePane.setPadding(new Insets(10,20,20,20));
        messagePane.setSpacing(10);
        messageScene = new Scene(messagePane);
        this.setScene(messageScene);
    }

    public void show(String title, String message) {
        setTitle(title);
        setMessageLabel(message);
        showAndWait();
    }
}
