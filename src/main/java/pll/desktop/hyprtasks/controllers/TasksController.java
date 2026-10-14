package pll.desktop.hyprtasks.controllers;

import javafx.scene.layout.VBox;
import pll.desktop.hyprtasks.models.Task;
import pll.desktop.hyprtasks.repositories.TasksRepository;

public class TasksController {

    public VBox todoSection;

    public void reloadTasks() {
        todoSection.getChildren().clear();
        for (Task task : TasksRepository.tasks().findAll()) {
            TaskCardController taskCard = new TaskCardController(task);
            todoSection.getChildren().add(taskCard);
        }
    }
}
