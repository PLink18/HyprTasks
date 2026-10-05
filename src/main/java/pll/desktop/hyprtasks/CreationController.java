package pll.desktop.hyprtasks;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class CreationController {

    public VBox addTaskPanel;

    public void closePanel(ActionEvent event) {
        addTaskPanel.setVisible(false);
    }
}
