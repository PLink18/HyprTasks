package pll.desktop.hyprtasks.controllers;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import pll.desktop.hyprtasks.models.Task;
import pll.desktop.hyprtasks.repositories.Repository;
import pll.desktop.hyprtasks.repositories.TasksRepository;

public class TasksController {

    public VBox addTaskPanel;

    public TextField titleField;
    public TextArea descriptionField;

    public VBox todo;

    public void createTask() {
        if (!isTitleEmpty()) {
            String title = pullText(titleField);
            String description = pullText(descriptionField);
            Task task = createTask(title, description);
            Repository.tasks().save(task);
        }
        closeCreationPanel();
    }

    private boolean isTitleEmpty() {
        return titleField.getText().isEmpty();
    }

    private String pullText(TextInputControl source) {
        String text = source.getText();
        source.clear();
        return text;
    }

    private Task createTask(String title, String description) {
        return new Task(title, description);
    }

    public void closeCreationPanel() {
        addTaskPanel.setVisible(false);
    }

    public void reloadTasks() {
        todo.getChildren().clear();
        for (Task task : TasksRepository.tasks().findAll()) {
            TaskCardController taskCard = new TaskCardController(task);
            todo.getChildren().add(taskCard);
        }
    }
}
