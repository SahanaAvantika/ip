package Ben.ui.gui;

import Ben.ui.Ben;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ben ben;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDiamond.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Omnitrix.png"));

    public static void introMsg(){
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");

        System.out.println("For a todo task, use: todo [task description]");
        System.out.println("Example: todo Buy groceries");

        System.out.println("For a deadline task, use: deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("Example: deadline Submit report /by 2025-03-15 23:59");

        System.out.println("For an event, use: event [event description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("Example: event Team meeting /from 2025-03-15 14:00 /to 2025-03-15 15:30");
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ben instance */
    public void setBen(Ben b) {
        ben = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ben.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
