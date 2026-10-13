package pll.desktop.hyprtasks.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pll.desktop.hyprtasks.models.Task;

import java.io.IOException;
public class TaskCardController extends VBox {

    private static final String TASK_CARD_PATH = "/fxml/TaskCard.fxml";

    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label priority;
    @FXML private Label deadline;

    public TaskCardController(Task task) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TASK_CARD_PATH));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        title.setText(task.getTitle());
        description.setText(task.getDescription());
        priority.setText(String.valueOf(task.getPriority()));
        deadline.setText(String.valueOf(task.getDeadline()));
    }
}
