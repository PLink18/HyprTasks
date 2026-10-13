package pll.desktop.hyprtasks.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainController {

    public VBox addTaskPanel;

    public VBox notes;
    public VBox profile;
    public TabPane tasks;

    @FXML
    private NavigationController navigationController;

    @FXML
    private TasksController tasksController;

    @FXML
    private void initialize() {
        navigationController.setOnViewChanged(this::switchView);
        reloadTasks();
    }

    public void openAddTaskPanel() {
        addTaskPanel.setVisible(!addTaskPanel.isVisible());
    }

    private void switchView(String buttonId) {
        switch (buttonId) {
            case "notesButton":
                notes.setVisible(true);
                tasks.setVisible(false);
                profile.setVisible(false);
                break;
            case "tasksButton":
                notes.setVisible(false);
                tasks.setVisible(true);
                profile.setVisible(false);
                break;
            case "profileButton":
                notes.setVisible(false);
                tasks.setVisible(false);
                profile.setVisible(true);
                break;
        }
    }

    public void reloadTasks() {
        tasksController.reloadTasks();
    }
}
