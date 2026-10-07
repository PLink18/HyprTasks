package pll.desktop.hyprtasks;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class MainController {

    public VBox addTaskPanel;

    public VBox notesView;
    public TabPane tasksView;
    public VBox profileView;

    public void openAddTaskPanel() {
        addTaskPanel.setVisible(!addTaskPanel.isVisible());
    }

    public void relocate(ActionEvent event) {
        ToggleButton button = (ToggleButton) event.getSource();
        switchView(button.getId());
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
