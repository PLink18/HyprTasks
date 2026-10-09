package pll.desktop.hyprtasks.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainController {

    public VBox addTaskPanel;

    public VBox notesView;
    public TabPane tasksView;
    public VBox profileView;

    @FXML
    private NavigationController navigationController;

    @FXML
    private void initialize() {
        navigationController.setOnViewChanged(this::switchView);
    }

    public void openAddTaskPanel() {
        addTaskPanel.setVisible(!addTaskPanel.isVisible());
    }

    private void switchView(String buttonId) {
        switch (buttonId) {
            case "notesButton":
                notesView.setVisible(true);
                tasksView.setVisible(false);
                profileView.setVisible(false);
                break;
            case "tasksButton":
                notesView.setVisible(false);
                tasksView.setVisible(true);
                profileView.setVisible(false);
                break;
            case "profileButton":
                notesView.setVisible(false);
                tasksView.setVisible(false);
                profileView.setVisible(true);
                break;
        }
    }
}
