package Buzzword;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import static settings.InitializationParameters.ERROR_DIALOG_BUTTON_LABEL;

/**
 * This class serves to present custom text messages to the user when
 * events occur. Note that it always provides the same controls, a label
 * with a message, and a single ok button.
 *
 * @author Richard McKenna, Ritwik Banerjee
 * @author ?
 * @version 1.0
 */
public class AppMessageDialogSingleton extends Stage {

    private static AppMessageDialogSingleton singleton = null;

    private Label messageLabel;

    private AppMessageDialogSingleton() { }

    /**
     * A static accessor method for getting the singleton object.
     *
     * @return The one singleton dialog of this object type.
     */
    public static AppMessageDialogSingleton getSingleton() {
        if (singleton == null)
            singleton = new AppMessageDialogSingleton();
        return singleton;
    }

    public void setMessageLabel(String messageLabelText) {
        messageLabel.setText(messageLabelText);
    }

    /**
     * This function fully initializes the singleton dialog for use.
     *
     * @param owner The window above which this dialog will be centered.
     */
    public void init(Stage owner) {
        initModality(Modality.WINDOW_MODAL); // modal => messages are blocked from reaching other windows
        initOwner(owner);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> this.close());

        VBox messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().add(closeButton);

        messagePane.setPadding(new Insets(80, 60, 80, 60));
        messagePane.setSpacing(20);

        Scene messageScene = new Scene(messagePane);
        this.setScene(messageScene);
    }

    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     *
     * @param title   The title to appear in the dialog window.
     * @param message Message to appear inside the dialog.
     */
    public void show(String title, String message) {
        setTitle(title); // set the dialog title
        setMessageLabel(message); // message displayed to the user
        showAndWait(); // opens the dialog, and waits for the user to resolve using one of the given choices
    }
}