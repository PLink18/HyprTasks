package pll.desktop.hyprtasks.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TaskOverviewController {

    public void closePanel(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.getParent().getParent().setVisible(false);
    }
}
