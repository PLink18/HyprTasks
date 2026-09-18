package pll.desktop.hyprtasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    final String MAIN_MARKUP = "/markup/main.fxml";

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_MARKUP));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
