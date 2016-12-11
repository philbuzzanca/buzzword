package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.Key;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class LoginWindow extends Stage {
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
            try {
                String username = loginField.getText();
                String fileToLoad = new String("users/"+username);
                File file = new File(fileToLoad);
                String password = passwordField.getText();
                if (!file.exists()){
                    System.out.println("User does not exist");
                }
                else{
                    String key = "kGH8Jg6iJbkFRuff";
                    Key aesKey = new SecretKeySpec(key.getBytes(),"AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                    JsonFactory jsonFactory = new JsonFactory();
                    //InputStream from = new FileInputStream(file);
                    JsonParser jsonParser = jsonFactory.createParser(Files.newInputStream(file.toPath()));
                    String loadedUsername;
                    String loadedEncryptedPassword = null;
                    while (!jsonParser.isClosed()) {
                        JsonToken token = jsonParser.nextToken();
                        
                        if (JsonToken.FIELD_NAME.equals(token)) {
                            String fieldname = jsonParser.getCurrentName();
                            switch (fieldname) {
                                case "Username":
                                    jsonParser.nextToken();
                                    loadedUsername = jsonParser.getValueAsString();
                                    Main.userData.setUsername(loadedUsername);
                                    break;
                                case "Encrypted password":
                                    jsonParser.nextToken();
                                    loadedEncryptedPassword = jsonParser.getValueAsString();
                                    Main.userData.setEncryptedPassword(loadedEncryptedPassword);
                                    break;
                                case "Dictionary words":
                                    jsonParser.nextToken();
                                    Main.userData.setDictionaryWords(jsonParser.getIntValue());
                                    break;
                                case "Animals":
                                    jsonParser.nextToken();
                                    Main.userData.setAnimals(jsonParser.getIntValue());
                                    break;
                                case "Common Names":
                                    jsonParser.nextToken();
                                    Main.userData.setCommonNames(jsonParser.getIntValue());
                                    break;
                                default:
                                    throw new JsonParseException(jsonParser, "Unable to load JSON data");
                            }
                        }
                    }
                    if (encrypt(password).equals(loadedEncryptedPassword)) {
                        LoginWindow.this.selection = ((Button) event.getSource()).getText();
                        LoginWindow.this.hide();
                    }
                    else {
                        System.out.println(password.toString()+"\n"+loadedEncryptedPassword);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        EventHandler<ActionEvent> cancelHandler = event -> {
            LoginWindow.this.selection = ((Button) event.getSource()).getText();
            LoginWindow.this.hide();
        };

        loginButton.setOnAction(loginHandler);
        cancelButton.setOnAction(cancelHandler);

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
