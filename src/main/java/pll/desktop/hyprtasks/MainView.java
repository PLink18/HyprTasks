package pll.desktop.hyprtasks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Нажми меня!");
        btn.setOnAction(e -> System.out.println("Hello, JavaFX!"));

        StackPane root = new StackPane(btn);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Hello JavaFX + Gradle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
