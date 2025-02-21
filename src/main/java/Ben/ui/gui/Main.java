package ben.ui.gui;

import ben.ui.Ben;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ben using FXML.
 */
public class Main extends Application {

    private Ben ben = new Ben("data/ben.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setBen(ben);

            stage.setOnCloseRequest(event -> {
                controller.bye();
            });

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

