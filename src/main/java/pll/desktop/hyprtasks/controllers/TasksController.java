package pll.desktop.hyprtasks.controllers;

import javafx.scene.layout.VBox;
import pll.desktop.hyprtasks.models.Task;
import pll.desktop.hyprtasks.repositories.TasksRepository;

public class TasksController {

    public VBox todo;

    public void reloadTasks() {
        todo.getChildren().clear();
        for (Task task : TasksRepository.tasks().findAll()) {
            TaskCardController taskCard = new TaskCardController(task);
            todo.getChildren().add(taskCard);
        }
    }
}
