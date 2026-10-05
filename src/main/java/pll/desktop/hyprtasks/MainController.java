package pll.desktop.hyprtasks;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class MainController {

    public VBox addTaskPanel;

    public void openAddTaskPanel(ActionEvent event) {
        addTaskPanel.setVisible(!addTaskPanel.isVisible());
    }
}
